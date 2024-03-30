package com.kidari.api.config.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    LECTURE_NOT_FOUND(404, "강연을 찾을 수 없습니다."),
    LECTURE_DUPLICATED(409, "이미 신청한 강연입니다."),
    HISTORY_NOT_FOUND(404, "신청 내역이 없습니다.")

    ; // End

    private final int status;
    private final String message;

    // 생성자 구성
    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}