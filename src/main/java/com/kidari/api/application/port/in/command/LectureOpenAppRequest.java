package com.kidari.api.application.port.in.command;

import com.kidari.api.adapter.in.web.request.LectureOpenWebRequest;
import com.kidari.api.config.exception.SelfValidating;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
@Builder // test
@AllArgsConstructor // test
@EqualsAndHashCode(callSuper = false)
public class LectureOpenAppRequest extends SelfValidating<LectureOpenAppRequest> {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String title;
    private String lecturer;
    private String location;

    @Min(value = 1, message = "입장 가능 인원은 1 이상이어야 합니다.")
    private Integer capacity;
    private LocalDateTime startDateTime;
    private String content;

    public LectureOpenAppRequest(LectureOpenWebRequest req) {
        this.title = req.getTitle();
        this.lecturer = req.getLecturer();
        this.location = req.getLocation();
        this.capacity = req.getCapacity();
        this.startDateTime = LocalDateTime.parse(req.getStartDateTime(), dateTimeFormatter);
        this.content = req.getContent();

        this.validateSelf();
    }
}
