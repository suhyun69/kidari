package com.kidari.api.adapter.out.persistence.mapper;

import com.kidari.api.adapter.out.persistence.entity.HistoryJpaEntity;
import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HistoryMapper {

    public HistoryJpaEntity mapToJpaEntity(LectureJpaEntity lectureT, String employeeNo) {
        return HistoryJpaEntity.builder()
                .lecture(lectureT)
                .employeeNo(employeeNo)
                .insDate(LocalDateTime.now())
                .build();
    }

    public History mapToDomainEntity(HistoryJpaEntity t) {
        return History.builder()
                .seq(t.getSeq())
                .lectureNo(t.getLecture().getNo())
                .employeeNo(t.getEmployeeNo())
                .insDate(t.getInsDate())
                .build();
    }
}
