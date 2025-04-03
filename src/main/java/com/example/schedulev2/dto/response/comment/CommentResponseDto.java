package com.example.schedulev2.dto.response.comment;

import com.example.schedulev2.dto.response.schedule.ScheduleResponseDto;
import com.example.schedulev2.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;
    private final String comment;
    private final String username;

    public CommentResponseDto(Long id, String comment, String username) {
        this.id = id;
        this.comment = comment;
        this.username = username;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContents(), comment.getUser().getUsername());
    }
}
