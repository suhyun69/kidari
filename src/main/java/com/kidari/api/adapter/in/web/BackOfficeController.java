package com.kidari.api.adapter.in.web;

import com.kidari.api.adapter.in.web.request.LectureOpenWebRequest;
import com.kidari.api.adapter.in.web.response.LectureInfo;
import com.kidari.api.application.port.in.GetLectureUseCase;
import com.kidari.api.application.port.in.LectureOpenUseCase;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backoffice")
@Tag(name = "BackOffice", description = "BackOffice API Document")
public class BackOfficeController {

    private final LectureOpenUseCase lectureOpenUseCase;
    private final GetLectureUseCase getLectureUseCase;

    @PostMapping("/lecture")
    @Operation(summary = "강연 등록", description = "강연자, 강연장, 신청 인원, 강연 시간, 강연 내용 입력")
    ResponseEntity<Long> lectureOpen(@Valid @RequestBody LectureOpenWebRequest request) {

        LectureOpenAppRequest appReq = new LectureOpenAppRequest(request);
        Long lectureNo = lectureOpenUseCase.lectureOpen(appReq);

        return ResponseEntity.ok(lectureNo);
    }

    @GetMapping("/lecture/{lectureNo}")
    @Operation(summary = "강연 조회")
    ResponseEntity<LectureInfo> getLecture(@PathVariable("lectureNo") Long lectureNo) {
        LectureInfo lectureInfo = getLectureUseCase.getLecture(lectureNo);
        return ResponseEntity.ok(lectureInfo);
    }

    @GetMapping("/lectures")
    @Operation(summary = "강연 목록", description = "전체 강연 목록")
    ResponseEntity<List<LectureInfo>> getLectures() {
        List<LectureInfo> lectureInfos = getLectureUseCase.getLectures();
        return ResponseEntity.ok(lectureInfos);
    }
}
