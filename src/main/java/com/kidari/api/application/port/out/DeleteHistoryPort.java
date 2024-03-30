package com.kidari.api.application.port.out;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;
import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;

public interface DeleteHistoryPort {
    Boolean deleteHistory(Long seq);
}
