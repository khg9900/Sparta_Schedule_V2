package com.example.schedulev2.repository;

import com.example.schedulev2.entity.User;
import com.example.schedulev2.exception.CannotFindException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CannotFindException("일치하는 회원 id : " + id + " 가 없습니다."));
    };

    default User findUserByEmailOrElseThrow(String email) {
        return findUserByEmail(email)
                .orElseThrow(() -> new CannotFindException("일치하는 회원 id : " + email + " 가 없습니다."));
    }
}
