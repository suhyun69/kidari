package com.kidari.api.adapter.in.web.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CancelLectureWebRequest {
    private Long lectureNo;

    @Size(min=5, max = 5, message = "사번은 5자리여야 합니다.")
    private String employeeNo;
}
