package com.example.chattingroom.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "HELLO~!";
    }

    @GetMapping("/success")
    public String loginSuccess() {
        return "login success~!";
    }

    @GetMapping("/api/test") // for test
    public String test(HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "testCookie");
        response.addCookie(cookie);
        return "cookie success~!";
    }
}
