package com.example.schedulev2.service;

import com.example.schedulev2.dto.response.schedule.ScheduleResponseDto;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.entity.User;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public ScheduleResponseDto save(String title, String contents, String username) {

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    // 선택 일정 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }

    @Transactional
    // 일정 수정
    public void updateSchedule(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateSchedule(title, contents);
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }

}
