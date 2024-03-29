package com.kidari.api.application.port.in;

import com.kidari.api.adapter.in.web.response.LectureInfo;

import java.util.List;

public interface GetLectureUseCase {
    LectureInfo getLecture(Long lectureNo);
    List<LectureInfo> getLectures();
}
