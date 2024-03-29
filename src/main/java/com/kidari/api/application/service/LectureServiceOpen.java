package com.kidari.api.application.service;

import com.kidari.api.application.port.in.LectureOpenUseCase;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddLecturePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureServiceOpen implements
        LectureOpenUseCase
{

    private final AddLecturePort addLecturePort;

    @Override
    public Long lectureOpen(LectureOpenAppRequest req) {
        Long lectureNo = addLecturePort.lectureOpen(req);
        return lectureNo;
    }
}
