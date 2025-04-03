package com.example.schedulev2.dto;

import com.example.schedulev2.entity.Comment;
import lombok.Getter;

public class CommentDto {

    // 댓글 요청
    @Getter
    public static class CommentRequest {

        private final String contents;

        public CommentRequest(String contents) {
            this.contents = contents;
        }
    }

    // 댓글 응답
    @Getter
    public static class CommentResponse {

        private final Long id;
        private final String comment;
        private final String username;

        public CommentResponse(Long id, String comment, String username) {
            this.id = id;
            this.comment = comment;
            this.username = username;
        }

        public static CommentResponse toDto(Comment comment) {
            return new CommentDto.CommentResponse(comment.getId(), comment.getContents(), comment.getUser().getUsername());
        }
    }

}
