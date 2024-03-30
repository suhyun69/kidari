package com.kidari.api.application.port.out;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;

public interface AddHistoryPort {
    Boolean addHistory(ApplyLectureAppRequest req);
}
