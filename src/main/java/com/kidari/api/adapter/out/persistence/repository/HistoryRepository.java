package com.kidari.api.adapter.out.persistence.repository;

import com.kidari.api.adapter.out.persistence.entity.HistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryJpaEntity, Long> {
}
