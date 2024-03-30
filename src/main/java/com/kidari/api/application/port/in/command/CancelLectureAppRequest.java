package com.kidari.api.application.port.in.command;

import com.kidari.api.adapter.in.web.request.ApplyLectureWebRequest;
import com.kidari.api.adapter.in.web.request.CancelLectureWebRequest;
import com.kidari.api.config.exception.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CancelLectureAppRequest extends SelfValidating<CancelLectureAppRequest> {
    private Long lectureNo;
    private String employeeNo;

    public CancelLectureAppRequest(CancelLectureWebRequest req) {
        this.lectureNo = req.getLectureNo();
        this.employeeNo = req.getEmployeeNo();

        this.validateSelf();
    }
}