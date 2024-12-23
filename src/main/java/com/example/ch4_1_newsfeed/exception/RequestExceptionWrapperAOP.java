package com.example.ch4_1_newsfeed.exception;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * mvc 에서 던져지는 예외를 응답 형식에 맞게 표준화하는 wrapper
 *
 */
@Aspect
@Component
public class RequestExceptionWrapperAOP {
    /**
     * Valid 검증 실패한 경우에 대해 예외 래핑
     * 실패한 모든 Validation에 대한 메시지들을 출력
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.controller.*(..))",
        throwing = "e"
    )
    public void wrapMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
            .map(val -> val.getField() + val.getDefaultMessage())
            .collect(Collectors.joining("\n"));
        throw new ResponseException(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * RequestBody 등으로 변환할 입력 값의 타입이 맞지 않는 경우에 대한 예외 래핑
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.controller.*(..))",
        throwing = "e"
    )
    public void wrapMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        throw new ResponseException("입력값 타입이 올바르지 않습니다.", HttpStatus.BAD_REQUEST);
    }

    /**
     * IllegalStateException 이 여러 상황에 사용될 수 있는 것을 고려해 이에 맞는 형식의 응답을 반환할 수 있도록 함
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.service.*(..))",
        throwing = "e"
    )
    public void wrapIllegalStateException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // IllegalStateException 활용 방식이 늘어나면 switch문으로 변경할 것
        if (e.getMessage().equals("내 정보가 존재하지 않습니다.")) {
            status = HttpStatus.NOT_FOUND;
        }

        throw new ResponseException(e.getMessage(), status);
    }

    /**
     * 쿼리 실행 중 데이터 일관성 문제가 발생한 경우에 대한 예외 래핑
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.repository.*(..))",
        throwing = "e"
    )
    public void wrapDataIntegrityViolationException(DataIntegrityViolationException e) {
        throw new ResponseException("데이터 일관성 문제", HttpStatus.BAD_REQUEST);
    }

    /**
     * 데이터 조회 결과가 존재하지 않는 경우에 대한 예외 래핑
     * 빈 리스트를 반환하는 경우에도
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.repository.*(..))",
        throwing = "e"
    )
    public void wrapEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        throw new ResponseException("해당 값이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }

    /**
     * 데이터 조회 결과가 예상보다 많은 케이스에 대해 별도로 래핑
     * 예: 단일 조회 쿼리 수행 결과로 2개 이상의 row가 조회된 경우
     * @param e
     */

    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.repository.*(..))",
        throwing = "e"
    )
    public void wrapIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException e) {
        throw new ResponseException("값의 수가 예상보다 많습니다.", HttpStatus.CONFLICT);
    }

    /**
     * 인가 처리를 필터에서 일괄적으로 수행할 경우 없어질 기능
     * @param e
     */
    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.controller.*(..))"
            + "execution(* com.example.ch4_1_newsfeed.service.*(..))"
            + "execution(* com.example.ch4_1_newsfeed.repository.*(..))",
        throwing = "e"
    )
    public void wrapNotAuthorizedException(NotAuthorizedException e) {
        throw new ResponseException("권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

    /**
     *
     * @param e
     * mvc 모델 상에서 나머지 예외들을 일괄적으로 처리
     */

    @AfterThrowing(
        pointcut = "execution(* com.example.ch4_1_newsfeed.controller.*(..))"
            + "execution(* com.example.ch4_1_newsfeed.service.*(..))"
            + "execution(* com.example.ch4_1_newsfeed.repository.*(..))",
        throwing = "e"
    )
    public void wrapRuntimeException(RuntimeException e) {
        throw new ResponseException("서버 내부 에러", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
