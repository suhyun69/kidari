package com.kidari.api.adapter.out.persistence.mapper;

import com.kidari.api.adapter.out.persistence.entity.HistoryJpaEntity;
import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public HistoryJpaEntity mapToJpaEntity(ApplyLectureAppRequest req) {
        return HistoryJpaEntity.builder()
                .lectureNo(req.getLectureNo())
                .employeeNo(req.getEmployeeNo())
                .build();
    }

    public History mapToDomainEntity(HistoryJpaEntity t) {
        return History.builder()
                .seq(t.getSeq())
                .lectureNo(t.getLectureNo())
                .employeeNo(t.getEmployeeNo())
                .build();
    }
}
