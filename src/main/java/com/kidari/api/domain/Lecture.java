package com.kidari.api.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@RequiredArgsConstructor
public class Lecture {
    private Long no;
    private String title;
    private String lecturer;
    private String location;
    private Integer capacity;
    private LocalDateTime startDateTime;
    private String content;
}