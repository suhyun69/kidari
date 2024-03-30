package com.kidari.api.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class History {
    private Long seq;
    private Long lectureNo;
    private String employeeNo;
}
