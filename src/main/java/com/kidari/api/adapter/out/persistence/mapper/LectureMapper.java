package com.kidari.api.adapter.out.persistence.mapper;

import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LectureMapper {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final HistoryMapper historyMapper;

    public LectureJpaEntity mapToJpaEntity(LectureOpenAppRequest req) {
        return LectureJpaEntity.builder()
                .title(req.getTitle())
                .lecturer(req.getLecturer())
                .location(req.getLocation())
                .capacity(req.getCapacity())
                .startDateTime(req.getStartDateTime())
                .content(req.getContent())
                .history(new ArrayList<>())
                .build();
    }

    public Lecture mapToDomainEntity(LectureJpaEntity t) {
        return Lecture.builder()
                .no(t.getNo())
                .title(t.getTitle())
                .lecturer(t.getLecturer())
                .location(t.getLocation())
                .capacity(t.getCapacity())
                .startDateTime(t.getStartDateTime())
                .content(t.getContent())
                .history(t.getHistory().stream().map(historyMapper::mapToDomainEntity).collect(Collectors.toList()))
                .build();
    }
}
