package com.kidari.api.adapter.out.persistence.repository;

import com.kidari.api.adapter.out.persistence.entity.HistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryJpaEntity, Long> {
    List<HistoryJpaEntity> findByEmployeeNo(String employeeNo);
}
