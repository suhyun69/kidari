package com.kidari.api.application.service;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class LectureServiceTest_Real {

    private LectureService lectureService;

    private static Integer NUMBER_OF_THREAD  = 100;
    private static Integer TEST_CAPACITY = 25;

    @Autowired
    public LectureServiceTest_Real(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Test
    void 강연신청_동시성_테스트() throws InterruptedException {

        //given
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREAD); // 스레드풀에 스레드 100개 준비
        CountDownLatch countDownLatch = new CountDownLatch(NUMBER_OF_THREAD);

        LectureOpenAppRequest lectureOpenReq = LectureOpenAppRequest.builder()
                .title("test")
                .lecturer("test")
                .location("test")
                .capacity(TEST_CAPACITY)
                .startDateTime(LocalDateTime.now())
                .content("test")
                .build();
        Long lectureNo = lectureService.lectureOpen(lectureOpenReq);

        List<ApplyLectureAppRequest> reqList = new ArrayList<>();
        for(int i=1; i<=NUMBER_OF_THREAD; i++) {
            reqList.add(new ApplyLectureAppRequest(lectureNo, String.format("K%04d", i)));
        }

        for(ApplyLectureAppRequest req : reqList) {
            executorService.execute(() -> {
                lectureService.applyLecture(req);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        //then
        assertThat(lectureService.getEmployees(lectureNo).size()).isEqualTo(TEST_CAPACITY);
    }
}