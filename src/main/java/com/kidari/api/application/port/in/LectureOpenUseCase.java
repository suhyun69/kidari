package com.kidari.api.application.port.in;

import com.kidari.api.application.port.in.command.LectureOpenAppRequest;

public interface LectureOpenUseCase {
    Long lectureOpen(LectureOpenAppRequest req);
}
