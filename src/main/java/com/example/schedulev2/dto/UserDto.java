package com.example.schedulev2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

    // 회원가입 요청
    @Getter
    public static class SignUpRequest {

        private final String username;
        private final String email;
        private final String password;

        public SignUpRequest(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }

    // 회원가입 응답
    @Getter
    public static class SignUpResponse {

        private final Long id;
        private final String username;
        private final String email;

        public SignUpResponse(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }
    }

    // 로그인 요청
    @Getter
    @AllArgsConstructor
    public static class LoginRequest {

        private final String email;
        private final String password;
    }

    // 로그인 응답
    @Getter
    public static class LoginResponse {

        private final Long id;
        private final String username;

        public LoginResponse(Long id, String username) {
            this.id = id;
            this.username = username;
        }
    }

    // 비밀번호 수정 요청
    @Getter
    public static class UpdatePasswordRequest {

        private final String oldPassword;
        private final String newPassword;

        public UpdatePasswordRequest(String oldPassword, String newPassword) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
        }
    }

    // 회원 조회 응답
    @Getter
    public static class UserResponse {
        private final String username;
        private final String email;

        public UserResponse(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

}
