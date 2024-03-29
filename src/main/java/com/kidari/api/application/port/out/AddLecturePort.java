package com.kidari.api.application.port.out;

import com.kidari.api.application.port.in.command.LectureOpenAppRequest;

public interface AddLecturePort {
    Long lectureOpen(LectureOpenAppRequest req);
}
