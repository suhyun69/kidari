package com.kidari.api.adapter.out.persistence;

import com.kidari.api.adapter.out.persistence.entity.HistoryJpaEntity;
import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import com.kidari.api.adapter.out.persistence.mapper.HistoryMapper;
import com.kidari.api.adapter.out.persistence.mapper.LectureMapper;
import com.kidari.api.adapter.out.persistence.repository.HistoryRepository;
import com.kidari.api.adapter.out.persistence.repository.LectureRepository;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.AddHistoryPort;
import com.kidari.api.application.port.out.AddLecturePort;
import com.kidari.api.application.port.out.DeleteHistoryPort;
import com.kidari.api.application.port.out.GetLecturePort;
import com.kidari.api.config.exception.BusinessException;
import com.kidari.api.config.exception.ErrorCode;
import com.kidari.api.domain.History;
import com.kidari.api.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements
        AddLecturePort
        , GetLecturePort
        , AddHistoryPort
        , DeleteHistoryPort
{

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

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

    @Override
    public List<Lecture> getLectures() {
        List<LectureJpaEntity> lectureTList = lectureRepository.findAll();
        return lectureTList.stream()
                .map(t -> lectureMapper.mapToDomainEntity(t))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean addHistory(ApplyLectureAppRequest req) {

        LectureJpaEntity lectureT = lectureRepository.findById(req.getLectureNo())
                .orElseThrow(() -> new BusinessException(ErrorCode.LECTURE_NOT_FOUND));

        try {
            HistoryJpaEntity historyT = historyMapper.mapToJpaEntity(lectureT, req.getEmployeeNo());
            historyRepository.save(historyT);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Boolean deleteHistory(Long seq) {

        try {
            HistoryJpaEntity historyT = historyRepository.findById(seq)
                    .orElseThrow(() -> new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND));
            historyRepository.delete(historyT);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
