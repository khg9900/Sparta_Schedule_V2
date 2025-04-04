package com.example.schedulev2.exception;

import org.springframework.http.HttpStatus;

public class LoginRequiredException extends ApplicationException {
    public LoginRequiredException() {
        super("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
    }
}
