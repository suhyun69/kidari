package com.kidari.api.adapter.out.persistence.repository;

import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LectureJpaEntity> findByNo(Long lectureNo);
}
