package com.example.schedulev2.service;

import com.example.schedulev2.dto.response.comment.CommentResponseDto;
import com.example.schedulev2.entity.Comment;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.CommentRepository;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 댓글 생성
    public CommentResponseDto save(Long scheduleId, String contents, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(contents);
        comment.setUser(findUser);
        comment.setSchedule(findSchedule);

        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), comment.getContents(), comment.getUser().getUsername());
    }

    // 일정별 전체 댓글 조회
    public List<CommentResponseDto> findAll(Long scheduleId) {
        return commentRepository.findBySchedule_Id(scheduleId).stream().map(CommentResponseDto::toDto).toList();
    }

    public void updateComment(Long id, String contents) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        findComment.updateComment(contents);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        commentRepository.delete(findComment);
    }
}
