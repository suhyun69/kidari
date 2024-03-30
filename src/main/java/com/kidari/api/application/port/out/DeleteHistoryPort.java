package com.kidari.api.application.port.out;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;

public interface DeleteHistoryPort {
    Boolean deleteHistory(CancelLectureAppRequest req);
}
