package com.example.schedulev2.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException() {
        super("작성자만 수정/삭제할 수 있습니다.", HttpStatus.UNAUTHORIZED);
    }
}
