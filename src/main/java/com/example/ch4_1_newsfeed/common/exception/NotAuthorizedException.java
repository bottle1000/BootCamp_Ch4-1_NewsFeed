package com.example.ch4_1_newsfeed.common.exception;

/**
 * 사용자에게 허가되지 않은 동작을 의미하는 예외
 * 예: 다른 사용자 정보를 수정하는 요청
 */

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}