package com.example.ch4_1_newsfeed.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public enum ExceptionMessage {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 형식의 응답힙니다"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 값 또는 파일이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final HttpStatus status;
    private final String message;

    public ResponseEntity<String> getStringResponse() {
        return ResponseEntity.status(this.status).body(this.message);
    }
}
