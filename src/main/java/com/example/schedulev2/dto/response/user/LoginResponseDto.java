package com.example.schedulev2.dto.response.user;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final Long id;
    private final String username;

    public LoginResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
