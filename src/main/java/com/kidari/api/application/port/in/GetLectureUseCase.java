package com.kidari.api.application.port.in;

import com.kidari.api.adapter.in.web.response.LectureInfo;

public interface GetLectureUseCase {
    LectureInfo getLecture(Long lectureNo);
}
