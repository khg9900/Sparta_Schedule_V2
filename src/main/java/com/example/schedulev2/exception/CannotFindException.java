package com.example.schedulev2.exception;

import org.springframework.http.HttpStatus;

public class CannotFindException extends ApplicationException {
    public CannotFindException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
