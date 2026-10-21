package com.blog.blogging_platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(
            RuntimeException exception) {

        HttpStatus status = exception.getMessage() != null
                && exception.getMessage().contains("not found")
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_REQUEST;

        Map<String, String> error = Map.of(
                "error", exception.getMessage() == null
                        ? "An unexpected error occurred"
                        : exception.getMessage()
        );

        return ResponseEntity.status(status).body(error);
    }
}
