package com.kidari.api.application.service;

import com.kidari.api.adapter.in.web.response.LectureInfo;
import com.kidari.api.application.port.in.*;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.*;
import com.kidari.api.config.exception.BusinessException;
import com.kidari.api.config.exception.ErrorCode;
import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService implements
        LectureOpenUseCase
        , GetLectureUseCase
        , ApplyLectureUseCase
        , CancelLectureUseCase
        , GetEmployeesUseCase
{

    private final AddLecturePort addLecturePort;
    private final GetLecturePort getLecturePort;
    private final AddHistoryPort addHistoryPort;
    private final DeleteHistoryPort deleteHistoryPort;
    private final GetHistoryPort getHistoryPort;

    @Override
    public Long lectureOpen(LectureOpenAppRequest req) {
        Long lectureNo = addLecturePort.lectureOpen(req);
        return lectureNo;
    }

    @Override
    public List<LectureInfo> getLectures() {
        List<Lecture> lectures = getLecturePort.getLectures();
        return lectures.stream()
                .map(LectureInfo::new)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean applyLecture(ApplyLectureAppRequest req) {

        Lecture lecture = getLecturePort.getLecture(req.getLectureNo());
        
        // 중복 신청 체크
        if(lecture.getHistory().stream().anyMatch(h -> h.getEmployeeNo().equals(req.getEmployeeNo()))) {
            throw new BusinessException(ErrorCode.LECTURE_DUPLICATED);
        }

        // 수강 인원 체크
        if(lecture.getHistory().size() >= lecture.getCapacity()) {
            // throw new BusinessException(ErrorCode.CAPACITY_FULLED);
            return false;
        }

        Boolean result = addHistoryPort.addHistory(req);

        return result;
    }

    @Override
    public Boolean cancelLecture(CancelLectureAppRequest req) {

        Lecture lecture = getLecturePort.getLecture(req.getLectureNo());

        History history = lecture.getHistory().stream()
                .filter(h -> h.getEmployeeNo().equals(req.getEmployeeNo()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorCode.HISTORY_NOT_FOUND));

        Boolean result = deleteHistoryPort.deleteHistory(history.getSeq());

        return result;
    }

    @Override
    public List<String> getEmployees(Long lectureNo) {

        return getLecturePort.getLecture(lectureNo).getHistory().stream()
                .map(History::getEmployeeNo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getLectures(String employeeNo) {

        return getHistoryPort.getHistories(employeeNo).stream()
                .map(History::getLectureNo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getAvailableLectures() {

        return getLecturePort.getLectures().stream()
                .filter(h -> isAvailable(h.getStartDateTime(), LocalDateTime.now()))
                .map(Lecture::getNo)
                .collect(Collectors.toList());
    }

    public Boolean isAvailable(LocalDateTime startDateTime, LocalDateTime now) {
        Boolean result = now.compareTo(startDateTime.minusDays(7)) >= 0 && startDateTime.plusDays(1).compareTo(now) >= 0;
        return result;
    }
}

