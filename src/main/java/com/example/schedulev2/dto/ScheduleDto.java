package com.example.schedulev2.dto;

import com.example.schedulev2.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScheduleDto {

    // 일정 요청
    @Getter
    public static class ScheduleRequest {

        @NotBlank(message = "일정의 제목을 입력해 주세요.")
        @Size(max=10, message="제목은 10글자 이내로 작성해 주세요.")
        private final String title;
        @NotBlank(message = "일정의 세부 내용을 입력해 주세요.")
        private final String contents;

        public ScheduleRequest(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }
    }


    // 일정 생성/수정 응답
    @Getter
    public static class ScheduleResponse {

        private final Long id;
        private final String title;
        private final String contents;

        public ScheduleResponse(Long id, String title, String contents) {
            this.id = id;
            this.title = title;
            this.contents = contents;
        }

        public static ScheduleResponse toDto(Schedule schedule) {
            return new ScheduleDto.ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents());
        }

    }

    // 일정 조회 응답
    @Getter
    public static class GetScheduleResponse {

        private final Long id;
        private final String title;
        private final String contents;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final String username;

        public GetScheduleResponse(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt, String username) {
            this.id = id;
            this.title = title;
            this.contents = contents;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.username = username;
        }

        public static GetScheduleResponse toDto(Schedule schedule) {
            return new ScheduleDto.GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt(), schedule.getUser().getUsername());
        }
    }
}
