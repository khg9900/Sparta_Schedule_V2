package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.exception.UnauthorizedException;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public ScheduleDto.ScheduleResponse save(String title, String contents, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleDto.ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    // 전체 일정 조회
    public Page<ScheduleDto.GetScheduleResponse> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Schedule> schedulePage = scheduleRepository.findAllByOrderByUpdatedAtDesc(pageable);

        return schedulePage.map(ScheduleDto.GetScheduleResponse::toDto);
    }

    // 선택 일정 조회
    public ScheduleDto.GetScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleDto.GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getUpdatedAt(), schedule.getUser().getUsername());
    }

    // 일정 수정
    @Transactional
    public void updateSchedule(Long id, String title, String contents, Long userId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        validateAuthor(userId, findSchedule);
        findSchedule.updateSchedule(title, contents);
    }

    // 일정 삭제
    public void deleteSchedule(Long id, Long userId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        validateAuthor(userId, findSchedule);
        scheduleRepository.delete(findSchedule);
    }

    // 작성자 일치 여부 확인
    public void validateAuthor(Long userId, Schedule scheudle) {
        if (!userId.equals(scheudle.getUser().getId())) {
            throw new UnauthorizedException();
        }
    }

}
