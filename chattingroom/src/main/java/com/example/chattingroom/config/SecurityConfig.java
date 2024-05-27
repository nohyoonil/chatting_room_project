package com.example.chattingroom.config;

import com.example.chattingroom.jwt.JwtFilter;
import com.example.chattingroom.oauth.handler.CustomAuthenticationSuccessHandler;
import com.example.chattingroom.service.CustomOauth2UserService;
import com.example.chattingroom.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomOauth2UserService oauth2UserService;
    private final CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest.requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/test")).permitAll() //for test
                                .anyRequest().authenticated())
                .headers(headersConfigurer ->
                        headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(loginConfigurer ->
                        loginConfigurer.authorizationEndpoint(authorizationEndpointConfig ->
                                        authorizationEndpointConfig.baseUri("/oauth2/authorization"))// /oauth2/authorization/google 등 oauth2 login 진입점
                                .redirectionEndpoint(redirectionEndpointConfig ->
                                        redirectionEndpointConfig.baseUri("/login/oauth2/code/**"))// oauth login 성공 시 해당 uri로 redirect
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig.userService(oauth2UserService))// redirectionEndpoint("/login/oauth2/code/**")로 redirect 될 때 여기서 처리 ***
                                .successHandler(successHandler))// oauth 로그인 성공 시 핸들링
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/")) //for test
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil);
    }
}
