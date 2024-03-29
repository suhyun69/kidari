package com.kidari.api.adapter.in.web;

import com.kidari.api.adapter.in.web.request.LectureOpenWebRequest;
import com.kidari.api.application.port.in.LectureOpenUseCase;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/backoffice")
@Tag(name = "BackOffice", description = "BackOffice API Document")
public class BackOfficeController {

    private final LectureOpenUseCase lectureOpenUseCase;

    @PostMapping("/lecture")
    @Operation(summary = "강연 등록")
    ResponseEntity<Long> lectureOpen(@RequestBody LectureOpenWebRequest request) {

        LectureOpenAppRequest appReq = new LectureOpenAppRequest(request);
        Long lectureNo = lectureOpenUseCase.lectureOpen(appReq);

        return ResponseEntity.ok(lectureNo);
    }
}
