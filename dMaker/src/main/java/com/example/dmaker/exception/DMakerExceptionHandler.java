package com.example.dmaker.exception;

import com.example.dmaker.dto.DMakerErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.dmaker.exception.DMakerErrorCode.INTERNAL_SEVER_ERROR;
import static com.example.dmaker.exception.DMakerErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class DMakerExceptionHandler {
    @ExceptionHandler(DMakerException.class)
    public DMakerErrorResponse handleException(
            DMakerException e,
            HttpServletRequest request){
        log.error("errorCode: {} , url: {}, message: {} ",
                e.getDMakerErrorCode(), request.getRequestURI(), e.getDetailMessage() );

        return DMakerErrorResponse.builder()
                .errorCode(e.getDMakerErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,  // get인데 Post 요청하거나
            MethodArgumentNotValidException.class  // 값이 유효성 검사에 걸릴때
    })
    public DMakerErrorResponse handleBadRequest(Exception e, HttpServletRequest request){
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return DMakerErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public DMakerErrorResponse handleException(Exception e, HttpServletRequest request){
        log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage());
        return DMakerErrorResponse.builder()
                .errorCode(INTERNAL_SEVER_ERROR)
                .errorMessage(INTERNAL_SEVER_ERROR.getMessage())
                .build();
    }




}
