package com.kidari.api.adapter.out.persistence;

import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.adapter.out.persistence.mapper.LectureMapper;
import com.kidari.api.adapter.out.persistence.repository.LectureRepository;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddLecturePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements
        AddLecturePort
{

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public Long lectureOpen(LectureOpenAppRequest req) {

        LectureJpaEntity lectureT = lectureRepository.save(lectureMapper.mapToJpaEntity(req));

        return lectureT.getNo();
    }
}
