package com.kidari.api.adapter.out.persistence;

import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.adapter.out.persistence.mapper.LectureMapper;
import com.kidari.api.adapter.out.persistence.repository.LectureRepository;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddLecturePort;
import com.kidari.api.application.port.out.GetLecturePort;
import com.kidari.api.config.exception.BusinessException;
import com.kidari.api.config.exception.ErrorCode;
import com.kidari.api.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements
        AddLecturePort
        , GetLecturePort
{

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public Long lectureOpen(LectureOpenAppRequest req) {
        LectureJpaEntity lectureT = lectureRepository.save(lectureMapper.mapToJpaEntity(req));
        return lectureT.getNo();
    }

    @Override
    public Lecture getLecture(Long lectureNo) {
        LectureJpaEntity lectureT = lectureRepository.findById(lectureNo)
                .orElseThrow(() -> new BusinessException(ErrorCode.LECTURE_NOT_FOUND));
        return lectureMapper.mapToDomainEntity(lectureT);
    }
}
