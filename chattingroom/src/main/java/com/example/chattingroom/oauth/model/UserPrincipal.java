package com.example.chattingroom.oauth.model;

import com.example.chattingroom.type.Provider;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Builder
public class UserPrincipal implements OAuth2User, UserDetails {

    private Long id;

    private String email;

    private String socialId;

    private Provider provider;

    public UserPrincipal(Long id, String email, String socialId, Provider provider) {
        this.id = id;
        this.email = email;
        this.socialId = socialId;
        this.provider = provider;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return this.socialId;
    }
}
