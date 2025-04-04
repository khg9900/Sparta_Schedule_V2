package com.example.schedulev2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

    // 회원가입 요청
    @Getter
    public static class SignUpRequest {

        @NotBlank(message = "이름을 입력해 주세요.")
        @Size(max=5, message="이름은 5글자를 초과할 수 없습니다.")
        private final String username;
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email
        private final String email;
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", message = "비밀번호 형식이 올바르지 않습니다. 8자 이상, 대소문자 포함, 숫자 및 특수문자(@$!%*?&#) 포함")
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

        @Email
        @NotBlank(message = "이메일을 입력해 주세요.")
        private final String email;
        @NotBlank(message = "비밀번호를 입력해 주세요.")
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

        @NotBlank(message = "현재 비밀번호를 입력해 주세요.")
        private final String oldPassword;
        @NotBlank(message = "새로운 비밀번호를 입력해 주세요.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", message = "비밀번호 형식이 올바르지 않습니다. 8자 이상, 대소문자 포함, 숫자 및 특수문자(@$!%*?&#) 포함")
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
