package com.kidari.api.adapter.in.web.response;

import com.kidari.api.domain.Lecture;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class LectureInfo {
    private Long no;
    private String title;
    private String lecturer;
    private String location;
    private Integer capacity;
    private String startDateTime;
    private String content;

    public LectureInfo(Lecture l) {
        this.no = l.getNo();
        this.title = l.getTitle();
        this.lecturer = l.getLecturer();
        this.location = l.getLocation();
        this.capacity = l.getCapacity();
        this.startDateTime = l.getStartDateTime();
        this.content = l.getContent();
    }
}
