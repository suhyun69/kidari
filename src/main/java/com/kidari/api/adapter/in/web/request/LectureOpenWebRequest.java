package com.kidari.api.adapter.in.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LectureOpenWebRequest {
    private String title;
    private String lecturer;
    private String location;

    @NotNull
    @Min(value = 1, message = "입장 가능 인원은 1 이상이어야 합니다.")
    private Integer capacity;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}", message = "startDateTime은(는) yyyy-MM-dd HH:mm 형식으로 입력하세요.")
    private String startDateTime;
    private String content;
}
