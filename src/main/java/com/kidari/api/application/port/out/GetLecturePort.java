package com.kidari.api.application.port.out;

import com.kidari.api.domain.Lecture;

public interface GetLecturePort {
    Lecture getLecture(Long lectureNo);
}
