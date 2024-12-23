package com.example.ch4_1_newsfeed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseException extends RuntimeException {
    protected final HttpStatus status;
    protected final String message;

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public ResponseEntity<Map<String, String>> getResponseEntity() {
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(this.status).body(body);
    }
}
