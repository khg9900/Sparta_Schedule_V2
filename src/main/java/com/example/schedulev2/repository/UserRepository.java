package com.example.schedulev2.repository;

import com.example.schedulev2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "일치하는 회원 id : " + id + " 가 없습니다."));
    };

    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "일치하는 회원명 : " + username + " 이(가) 없습니다."));
    }
}
