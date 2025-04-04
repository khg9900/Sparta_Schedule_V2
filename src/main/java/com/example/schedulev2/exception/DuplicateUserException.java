package com.example.schedulev2.exception;

import org.springframework.http.HttpStatus;

public class DuplicateUserException extends ApplicationException {
    public DuplicateUserException() {
        super("이미 가입된 이메일입니다.", HttpStatus.CONFLICT);
    }
}
