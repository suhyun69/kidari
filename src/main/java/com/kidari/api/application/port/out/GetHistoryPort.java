package com.kidari.api.application.port.out;

import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;

import java.util.List;

public interface GetHistoryPort {
    List<History> getHistories(String employeeNo);
}
