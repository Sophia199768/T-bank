package com.example.tbanks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcHandler {

    @ExceptionHandler(CurrException.class)
    public ResponseEntity<String> Message(CurrException currException) {
        return new ResponseEntity<>(currException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> ServerMessage(ServerException serverException) {
        return new ResponseEntity<>(serverException.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


}
