package com.example.schedulev2.repository;

import com.example.schedulev2.entity.Comment;
import com.example.schedulev2.exception.CannotFindException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findBySchedule_IdOrderByUpdatedAtDesc(Long scheduleId);

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CannotFindException( "일치하는 댓글 id : " + id + " 가 없습니다."));
    }

}
