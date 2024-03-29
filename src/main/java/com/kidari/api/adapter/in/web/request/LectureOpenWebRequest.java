package com.kidari.api.adapter.in.web.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LectureOpenWebRequest {
    private String lecturer;
    private String location;
    private Integer capacity;
    private String startDateTime;
    private String content;
}
