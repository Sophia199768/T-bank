package com.example.tbankhomework.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomLinkedListException extends Exception {
    public  CustomLinkedListException(String message) {
        super(message);
    }
}
