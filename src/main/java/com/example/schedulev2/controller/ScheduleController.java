package com.example.schedulev2.controller;

import com.example.schedulev2.common.Const;
import com.example.schedulev2.dto.ScheduleDto;
import com.example.schedulev2.dto.UserDto;
import com.example.schedulev2.service.ScheduleService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ScheduleDto.ScheduleResponse> save(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @Valid @RequestBody ScheduleDto.ScheduleRequest requestDto
    ) {
        ScheduleDto.ScheduleResponse scheduleResponseDto =
                scheduleService.save(requestDto.getTitle(), requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회 (페이징 처리)
    @GetMapping
    public ResponseEntity<List<ScheduleDto.GetScheduleResponse>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ScheduleDto.GetScheduleResponse> scheduleResponseDtoList = scheduleService.findAll(page, size);

        return new ResponseEntity<>(scheduleResponseDtoList.getContent(), HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto.GetScheduleResponse> findById(@PathVariable Long id) {
        ScheduleDto.GetScheduleResponse scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @PathVariable Long id,
            @Valid @RequestBody ScheduleDto.ScheduleRequest requestDto
    ) {
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents(), loginUser.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = Const.LOGIN_USER, required = false) UserDto.LoginResponse loginUser,
            @PathVariable Long id
    ) {
        scheduleService.deleteSchedule(id, loginUser.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
