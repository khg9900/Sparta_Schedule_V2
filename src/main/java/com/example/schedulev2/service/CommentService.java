package com.example.schedulev2.service;

import com.example.schedulev2.dto.CommentDto;
import com.example.schedulev2.entity.Comment;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.exception.UnauthorizedException;
import com.example.schedulev2.repository.CommentRepository;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 댓글 생성
    public CommentDto.CommentResponse save(Long scheduleId, String contents, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(contents);
        comment.setUser(findUser);
        comment.setSchedule(findSchedule);

        commentRepository.save(comment);

        return new CommentDto.CommentResponse(comment.getId(), comment.getContents(), comment.getUser().getUsername());
    }

    // 일정별 전체 댓글 조회
    public List<CommentDto.CommentResponse> findAll(Long scheduleId) {
        return commentRepository.findBySchedule_IdOrderByUpdatedAtDesc(scheduleId).stream().map(CommentDto.CommentResponse::toDto).toList();
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long id, String contents, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        validateAuthor(userId, findComment);
        findComment.updateComment(contents);
    }

    // 댓글 삭제
    public void deleteComment(Long id, Long userId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        validateAuthor(userId, findComment);
        commentRepository.delete(findComment);
    }

    // 작성자 일치 여부 확인
    public void validateAuthor(Long userId, Comment comment) {
        if (!userId.equals(comment.getUser().getId())) {
            throw new UnauthorizedException();
        }
    }
}
