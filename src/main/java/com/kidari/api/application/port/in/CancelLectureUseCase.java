package com.kidari.api.application.port.in;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;

public interface CancelLectureUseCase {
    Boolean cancelLecture(CancelLectureAppRequest req);
}
