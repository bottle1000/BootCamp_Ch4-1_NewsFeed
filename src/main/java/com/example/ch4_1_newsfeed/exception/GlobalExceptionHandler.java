package com.example.ch4_1_newsfeed.exception;

import org.springframework.dao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        MethodArgumentTypeMismatchException.class,
        DataIntegrityViolationException.class
    })
    public ResponseEntity<Map<String, String>> handleBadRequest(Exception e) {
        return ExceptionMessage.BAD_REQUEST.getStringResponse();
    }

    @ExceptionHandler(NotValidSessionException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorized(Exception e) {
        return ExceptionMessage.UNAUTHORIZED.getStringResponse();
    }

    // 하위의 클래스들부터 먼저 예외처리가 적용됨
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(Exception e) {
        return ExceptionMessage.NOT_FOUND.getStringResponse();
    }

    // EmptyResultDataAccessException 의 상위 클래스이므로 결과값이 없는 경우는 처리되지 않음
    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Map<String, String>> handleConflict(Exception e) {
        return ExceptionMessage.CONFLICT.getStringResponse();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleServerError(Exception e) {
        return ExceptionMessage.INTERNAL_SERVER_ERROR.getStringResponse();
    }
}
