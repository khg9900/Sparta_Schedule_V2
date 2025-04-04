package com.example.schedulev2.config;

import com.example.schedulev2.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleApplicationException(ApplicationException ex) {
        return getErrorResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Valid 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        // field 에러
        List<Map<String, String>> fieldErrors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> errors = new LinkedHashMap<>();
            errors.put("field", error.getField());
            errors.put("message", error.getDefaultMessage());

            fieldErrors.add(errors);
        }

        // 에러 메시지
        Map<String, Object> errorResponse = new LinkedHashMap<>();

        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", ex.getBody().getStatus());
        errorResponse.put("error", ex.getStatusCode());
        errorResponse.put("message", "잘못된 입력값입니다.");
        errorResponse.put("path", request.getRequestURI());
        errorResponse.put("fieldErrors", fieldErrors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> getErrorResponse(HttpStatus status, String message) {

        Map<String, Object> errorResponse = new LinkedHashMap<>();

        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.name());
        errorResponse.put("message", message);
        errorResponse.put("path", request.getRequestURI());

        return new ResponseEntity<>(errorResponse, status);
    }
}
