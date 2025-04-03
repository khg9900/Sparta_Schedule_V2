package com.example.schedulev2.controller;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.dto.request.user.LoginRequestDto;
import com.example.schedulev2.dto.request.user.SignUpRequestDto;
import com.example.schedulev2.dto.request.user.UpdatePasswordRequestDto;
import com.example.schedulev2.dto.response.user.LoginResponseDto;
import com.example.schedulev2.dto.response.user.SignUpResponseDto;
import com.example.schedulev2.dto.response.user.UserResponseDto;
import com.example.schedulev2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto =
                userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request
    ) {

        LoginResponseDto loginResponseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, loginResponseDto);

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {
        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
