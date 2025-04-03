package com.example.schedulev2.controller;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.dto.request.comment.CommentRequestDto;
import com.example.schedulev2.dto.response.comment.CommentResponseDto;
import com.example.schedulev2.dto.response.user.LoginResponseDto;
import com.example.schedulev2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules/{schedule_id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable(value = "schedule_id") Long scheduleId,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) LoginResponseDto loginUser,
            @RequestBody CommentRequestDto requestDto
    ) {
        CommentResponseDto commentResponseDto = commentService.save(scheduleId, requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 일정별 전체 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable(value = "schedule_id") Long scheduleId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto
    ) {
        commentService.updateComment(id, requestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
