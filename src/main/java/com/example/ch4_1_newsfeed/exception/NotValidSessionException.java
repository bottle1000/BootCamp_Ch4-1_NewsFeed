package com.example.ch4_1_newsfeed.exception;

public class NotValidSessionException extends RuntimeException {
    public NotValidSessionException(String message) {
        super(message);
    }
}
