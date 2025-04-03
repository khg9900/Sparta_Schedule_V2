package com.example.schedulev2.dto.response.schedule;

import com.example.schedulev2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String username;

    public GetScheduleResponseDto(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
    }

    public static GetScheduleResponseDto toDto(Schedule schedule) {
        return new GetScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt(), schedule.getUser().getUsername());
    }
}
