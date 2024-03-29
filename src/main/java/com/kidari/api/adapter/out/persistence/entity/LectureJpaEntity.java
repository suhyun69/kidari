package com.kidari.api.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecture")
public class LectureJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;

    private String lecturer;
    private String location;
    private Integer capacity;
    private String startDateTime;
    private String content;
}
