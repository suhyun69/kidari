package com.kidari.api.adapter.in.web.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ApplyLectureWebRequest {
    private Long lectureNo;
    private String employeeNo;
}
