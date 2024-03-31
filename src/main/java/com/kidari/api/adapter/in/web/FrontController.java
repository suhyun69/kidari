package com.kidari.api.adapter.in.web;

import com.kidari.api.adapter.in.web.request.ApplyLectureWebRequest;
import com.kidari.api.adapter.in.web.request.CancelLectureWebRequest;
import com.kidari.api.adapter.in.web.request.LectureOpenWebRequest;
import com.kidari.api.application.port.in.ApplyLectureUseCase;
import com.kidari.api.application.port.in.CancelLectureUseCase;
import com.kidari.api.application.port.in.GetLectureUseCase;
import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.CancelLectureAppRequest;
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
@RequestMapping("/v1/front")
@Tag(name = "Front", description = "Front API Document")
public class FrontController {

    private final ApplyLectureUseCase applyLectureUseCase;
    private final CancelLectureUseCase cancelLectureUseCase;
    private final GetLectureUseCase getLectureUseCase;

    @PostMapping("/apply")
    @Operation(summary = "강연 신청", description = "사번 입력, 같은 강연 중복 신청 제한")
    ResponseEntity<String> applyLecture(@Valid @RequestBody ApplyLectureWebRequest request) {

        ApplyLectureAppRequest appReq = new ApplyLectureAppRequest(request);
        Boolean result = applyLectureUseCase.applyLecture(appReq);
        String message = result ? "강연 신청이 완료되었습니다." : "강연 신청에 실패했습니다.";

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "신청한 강연 취소", description = "")
    ResponseEntity<String> cancelLecture(@Valid @RequestBody CancelLectureWebRequest request) {

        CancelLectureAppRequest appReq = new CancelLectureAppRequest(request);
        Boolean result = cancelLectureUseCase.cancelLecture(appReq);
        String message = result ? "강연 신청 취소가 완료되었습니다." : "강연 신청 취소에 실패했습니다.";

        return ResponseEntity.ok(message);
    }

    @GetMapping("/lectures/{employeeNo}")
    @Operation(summary = "신청 내역 조회", description = "사번 입력")
    ResponseEntity<List<Long>> getLectures(@PathVariable("employeeNo") String employeeNo) {
        List<Long> lectureList = getLectureUseCase.getLectures(employeeNo);
        return ResponseEntity.ok(lectureList);
    }
    
    @GetMapping("/lectures")
    @Operation(summary = "강연 목록", description = "전체 강연 목록")
    ResponseEntity<List<Long>> getAvailableLectures() {
        List<Long> lectureList = getLectureUseCase.getAvailableLectures();
        return ResponseEntity.ok(lectureList);
    }
}
