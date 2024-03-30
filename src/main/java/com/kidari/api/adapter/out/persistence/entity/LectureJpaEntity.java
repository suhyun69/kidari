package com.kidari.api.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecture")
public class LectureJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LECTURE_NO")
    private Long no;

    private String title;
    private String lecturer;
    private String location;
    private Integer capacity;
    private LocalDateTime startDateTime;
    private String content;

    @OneToMany(mappedBy = "lecture")
    private List<HistoryJpaEntity> history = new ArrayList<>();
}
