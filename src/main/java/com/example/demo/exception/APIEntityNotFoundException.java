package com.example.demo.exception;

public class APIEntityNotFoundException extends RuntimeException {
    public APIEntityNotFoundException(String message) {
        super(message);
    }
}
