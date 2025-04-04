package com.example.schedulev2.exception;

import org.springframework.http.HttpStatus;

public class CannotDeleteException extends ApplicationException {
    public CannotDeleteException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
