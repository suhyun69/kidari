package com.kidari.api.application.port.in;

import com.kidari.api.adapter.in.web.response.LectureInfo;

import java.util.List;

public interface GetLectureUseCase {
    List<LectureInfo> getLectures();
    List<Long> getLectures(String employeeNo);
    List<Long> getAvailableLectures();
}
