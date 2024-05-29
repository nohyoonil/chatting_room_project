package com.example.chattingroom.exception;

import jakarta.servlet.ServletException;

public class UnAuthenticatedException extends ServletException {

    public UnAuthenticatedException(String message) {super(message);}
}
