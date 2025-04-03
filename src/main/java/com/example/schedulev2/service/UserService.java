package com.example.schedulev2.service;

import com.example.schedulev2.config.PasswordEncoder;
import com.example.schedulev2.dto.response.user.LoginResponseDto;
import com.example.schedulev2.dto.response.user.SignUpResponseDto;
import com.example.schedulev2.dto.response.user.UserResponseDto;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public SignUpResponseDto signUp(String username, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, email, encodedPassword);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // 로그인
    public LoginResponseDto login(String email, String password) {

        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        if (!passwordEncoder.matches(password, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(findUser.getId(), findUser.getUsername());
    }

    // 회원조회
    public UserResponseDto findById(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);

        findUser.updatePassword(encodedPassword);
    }

    // 회원탈퇴
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }

}
