package com.kidari.api.application.port.in;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;

public interface ApplyLectureUseCase {
    Boolean applyLecture(ApplyLectureAppRequest req);
}
