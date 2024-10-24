package com.example.tbank.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
       super(message);
    }
}
