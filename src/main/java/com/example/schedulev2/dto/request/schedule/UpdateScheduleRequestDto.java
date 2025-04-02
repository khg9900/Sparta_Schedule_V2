package com.example.schedulev2.dto.request.schedule;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final String title;
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
