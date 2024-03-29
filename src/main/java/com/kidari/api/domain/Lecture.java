package com.kidari.api.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class Lecture {
    private Long no;
    private String title;
    private String lecturer;
    private String location;
    private Integer capacity;
    private String startDateTime;
    private String content;
}