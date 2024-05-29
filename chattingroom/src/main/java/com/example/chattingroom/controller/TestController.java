package com.example.chattingroom.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/")
    public String hello() {
        return "HELLO~!";
    }

    @GetMapping("/success")
    public String loginSuccess() {
        return "login success~!";
    }

    @PostMapping("/redis") // for test
    public String redisInsertTest(@RequestParam String key, @RequestParam String value) {
        ValueOperations<String, String> valueOperation = redisTemplate.opsForValue();
        valueOperation.set(key, value);
        return "insert success";
    }

    @GetMapping("/redis/{key}") // for test
    public ResponseEntity<String> redisGetTest(@PathVariable String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        return ResponseEntity.ok(key + " : " + value);
    }
}
