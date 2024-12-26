package com.example.ch4_1_newsfeed.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 각 예외 별 상황을 클라이언트도 적당히 알 수 있도록 응답 메시지를 만들어 전달하는 클래스
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * RequestBody 등으로 변환할 입력 값의 타입이 맞지 않는 경우에 대한 예외 래핑
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, "입력값 타입이 올바르지 않습니다.");
    }

    /**
     * IllegalStateException 이 여러 상황에 사용될 수 있는 것을 고려해 이에 맞는 형식의 응답을 반환할 수 있도록 함
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // IllegalStateException 활용 방식이 늘어나면 switch문으로 변경할 것
        if (e.getMessage().equals("내 정보가 존재하지 않습니다.")) {
            status = HttpStatus.NOT_FOUND;
        }
        return createMessageResponseEntity(status, e.getMessage());
    }

    /**
     * 쿼리 실행 중 데이터 일관성 문제가 발생한 경우에 대한 예외 래핑
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, "데이터 일관성 문제");
    }

    /**
     * JPA 쿼리 매개변수로 NULL이 들어갈 경우 발생하는 예외 래핑
     * 가능하면 사용되지 않도록 구현할 것
     */
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, "필요한 데이터가 존재하지 않습니다.");
    }

    /**
     * 데이터 조회 결과가 존재하지 않는 경우에 대한 예외 래핑
     * 빈 리스트를 반환하는 경우에도 예외처리를 수행하도록 함
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, String>> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return createMessageResponseEntity(HttpStatus.NOT_FOUND, "해당 값이 존재하지 않습니다.");
    }

    /**
     * 데이터 조회 결과가 예상보다 많은 케이스에 대해 별도로 래핑
     * 예: 단일 조회 쿼리 수행 결과로 2개 이상의 row가 조회된 경우
     */
    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException e) {
        return createMessageResponseEntity(HttpStatus.CONFLICT, "조회 결과가 예상보다 많습니다.");
    }

    /**
     * Spring에서 직접 처리해 던제는 예외들에 대한 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.joining("\n"));
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        String messages = Arrays.stream(e.getDetailMessageArguments())
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, messages);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(NoResourceFoundException e) {
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, "Path Variable이 포합되어야 합니다.");
    }

    /**
     * mvc 모델 상에서 나머지 예외들을 일괄적으로 처리
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        log.info(
            "exception: {}, message: {}, stacktrace: {}",
            e.getClass().getName(), e.getMessage(), e.getStackTrace()
        );
        return createMessageResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");
    }

    private ResponseEntity<Map<String, String>> createMessageResponseEntity(HttpStatus status, String message) {
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
