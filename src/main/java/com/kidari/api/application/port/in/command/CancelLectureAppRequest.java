package com.kidari.api.application.port.in.command;

import com.kidari.api.adapter.in.web.request.ApplyLectureWebRequest;
import com.kidari.api.adapter.in.web.request.CancelLectureWebRequest;
import com.kidari.api.config.exception.SelfValidating;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CancelLectureAppRequest extends SelfValidating<CancelLectureAppRequest> {
    private Long lectureNo;

    @Size(min=5, max = 5, message = "사번은 5자리여야 합니다.")
    private String employeeNo;

    public CancelLectureAppRequest(CancelLectureWebRequest req) {
        this.lectureNo = req.getLectureNo();
        this.employeeNo = req.getEmployeeNo();

        this.validateSelf();
    }
}