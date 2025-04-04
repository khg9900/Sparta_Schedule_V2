package com.example.schedulev2.service;

import com.example.schedulev2.config.PasswordEncoder;
import com.example.schedulev2.dto.UserDto;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.exception.CannotDeleteException;
import com.example.schedulev2.exception.DuplicateUserException;
import com.example.schedulev2.exception.PasswordMismatchException;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public UserDto.SignUpResponse signUp(String username, String email, String password) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 회원 이메일 존재 여부 확인
        if(userRepository.findUserByEmail(email).isPresent()) {
            throw new DuplicateUserException();
        }

        // 회원 생성
        User user = new User(username, email, encodedPassword);
        User savedUser = userRepository.save(user);

        return new UserDto.SignUpResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // 로그인
    public UserDto.LoginResponse login(String email, String password) {

        User findUser = userRepository.findUserByEmailOrElseThrow(email);

        // 비밀번호 일치 여부 확인
        isCorrectPassword(password, findUser);

        return new UserDto.LoginResponse(findUser.getId(), findUser.getUsername());
    }

    // 회원조회
    public UserDto.UserResponse findById(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        return new UserDto.UserResponse(findUser.getUsername(), findUser.getEmail());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        // 비밀번호 일치 여부 확인
        isCorrectPassword(oldPassword, findUser);

        // 새로운 비밀번호 암호화 후 수정
        String encodedPassword = passwordEncoder.encode(newPassword);
        findUser.updatePassword(encodedPassword);
    }

    // 회원탈퇴
    public void delete(Long id, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        // 비밀번호 일치 여부 확인
        isCorrectPassword(password, findUser);

        // 삭제
        try {
            userRepository.delete(findUser);
        } catch (DataIntegrityViolationException e) {
            throw new CannotDeleteException("작성한 일정을 삭제한 후 다시 시도해 주세요.");
        }
    }

    // 비밀번호 일치 여부 확인
    public void isCorrectPassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMismatchException();
        }
    }
}
