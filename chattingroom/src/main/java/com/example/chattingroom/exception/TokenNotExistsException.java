package com.example.chattingroom.exception;

public class TokenNotExistsException extends RuntimeException{
    public TokenNotExistsException(String message) {
        super(message);
    }
}
