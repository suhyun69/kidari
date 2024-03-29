package com.kidari.api.adapter.out.persistence.repository;

import com.kidari.api.adapter.out.persistence.entity.LectureJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {
}
