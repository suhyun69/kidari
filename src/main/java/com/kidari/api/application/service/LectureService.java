package com.kidari.api.application.service;

import com.kidari.api.adapter.in.web.response.LectureInfo;
import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.application.port.in.ApplyLectureUseCase;
import com.kidari.api.application.port.in.CancelLectureUseCase;
import com.kidari.api.application.port.in.GetLectureUseCase;
import com.kidari.api.application.port.in.LectureOpenUseCase;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddHistoryPort;
import com.kidari.api.application.port.out.AddLecturePort;
import com.kidari.api.application.port.out.DeleteHistoryPort;
import com.kidari.api.application.port.out.GetLecturePort;
import com.kidari.api.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService implements
        LectureOpenUseCase
        , GetLectureUseCase
        , ApplyLectureUseCase
        , CancelLectureUseCase
{

    private final AddLecturePort addLecturePort;
    private final GetLecturePort getLecturePort;
    private final AddHistoryPort addHistoryPort;
    private final DeleteHistoryPort deleteHistoryPort;

    @Override
    public Long lectureOpen(LectureOpenAppRequest req) {
        Long lectureNo = addLecturePort.lectureOpen(req);
        return lectureNo;
    }

    @Override
    public LectureInfo getLecture(Long lectureNo) {
        Lecture lecture = getLecturePort.getLecture(lectureNo);
        return new LectureInfo(lecture);
    }

    @Override
    public List<LectureInfo> getLectures() {
        List<Lecture> lecture = getLecturePort.getLectures();
        return lecture.stream()
                .map(LectureInfo::new)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean applyLecture(ApplyLectureAppRequest req) {
        
        // 강연 목록 조회

        Boolean result = addHistoryPort.addHistory(req);

        return result;
    }

    @Override
    public Boolean cancelLecture(CancelLectureAppRequest req) {
        
        // 강연 목록 조회

        Boolean result = deleteHistoryPort.deleteHistory(req);

        return result;
    }
}
