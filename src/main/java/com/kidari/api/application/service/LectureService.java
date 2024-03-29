package com.kidari.api.application.service;

import com.kidari.api.adapter.in.web.response.LectureInfo;
import com.kidari.api.application.port.in.GetLectureUseCase;
import com.kidari.api.application.port.in.LectureOpenUseCase;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddLecturePort;
import com.kidari.api.application.port.out.GetLecturePort;
import com.kidari.api.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService implements
        LectureOpenUseCase
        , GetLectureUseCase
{

    private final AddLecturePort addLecturePort;
    private final GetLecturePort getLecturePort;

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
}
