package com.example.schedulev2.controller;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.dto.CommentDto;
import com.example.schedulev2.dto.UserDto;
import com.example.schedulev2.service.CommentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CommentDto.CommentResponse> save(
            @PathVariable(value = "schedule_id") Long scheduleId,
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @Valid @RequestBody CommentDto.CommentRequest requestDto
    ) {
        CommentDto.CommentResponse commentResponseDto =
                commentService.save(scheduleId, requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 일정별 전체 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentDto.CommentResponse>> findAll(@PathVariable(value = "schedule_id") Long scheduleId) {
        List<CommentDto.CommentResponse> commentResponseDtoList = commentService.findAll(scheduleId);

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @PathVariable Long id,
            @RequestBody CommentDto.CommentRequest requestDto
    ) {
        commentService.updateComment(id, requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @PathVariable Long id
    ) {
        commentService.deleteComment(id, loginUser.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
