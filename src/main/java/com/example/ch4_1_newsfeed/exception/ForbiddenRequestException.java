package com.example.ch4_1_newsfeed.exception;

// 요청에 인증 정보(쿠키, 토큰)은 포함되어 있지만 자신의 권한 밖의 요청일 때 사용할 것
// 예: 다른 사용자의 정보를 수정하는 요청을 받았을 때
public class ForbiddenRequestException extends RuntimeException {
    public ForbiddenRequestException(String message) {
        super(message);
    }
}
