package com.kidari.api.application.port.in.command;

import com.kidari.api.adapter.in.web.request.LectureOpenWebRequest;
import lombok.Value;

@Value
public class LectureOpenAppRequest {
    private String title;
    private String lecturer;
    private String location;
    private Integer capacity;
    private String startDateTime;
    private String content;

    public LectureOpenAppRequest(LectureOpenWebRequest req) {
        this.title = req.getTitle();
        this.lecturer = req.getLecturer();
        this.location = req.getLocation();
        this.capacity = req.getCapacity();
        this.startDateTime = req.getStartDateTime();
        this.content = req.getContent();
    }
}
