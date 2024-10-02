package com.example.tbank.services;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
       super(message);
    }
}
