package com.example.schedulev2.dto;

import com.example.schedulev2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

public class ScheduleDto {

    // 일정 생성 요청
    @Getter
    public static class CreateScheduleRequest {

        private final String title;
        private final String contents;

        public CreateScheduleRequest(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }
    }

    // 일정 수정 요청
    @Getter
    public static class UpdateScheduleRequest {

        private final String title;
        private final String contents;

        public UpdateScheduleRequest(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }
    }

    // 일정 조회 요청
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

    // 일정 목록 조회 요청
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
