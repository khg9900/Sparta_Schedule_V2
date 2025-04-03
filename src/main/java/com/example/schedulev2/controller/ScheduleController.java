package com.example.schedulev2.controller;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.dto.request.schedule.CreateScheduleRequestDto;
import com.example.schedulev2.dto.request.schedule.UpdateScheduleRequestDto;
import com.example.schedulev2.dto.response.schedule.GetScheduleResponseDto;
import com.example.schedulev2.dto.response.schedule.ScheduleResponseDto;
import com.example.schedulev2.dto.response.user.LoginResponseDto;
import com.example.schedulev2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) LoginResponseDto loginUser,
            @RequestBody CreateScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회 (페이징 처리)
    @GetMapping
    public ResponseEntity<List<GetScheduleResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<GetScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll(page, size);

        return new ResponseEntity<>(scheduleResponseDtoList.getContent(), HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto requestDto
    ) {
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
