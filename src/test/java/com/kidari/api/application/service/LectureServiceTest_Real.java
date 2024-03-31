package com.kidari.api.application.service;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

    @Autowired
    public LectureServiceTest_Real(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @Test
    void 강연신청_동시성_테스트() throws InterruptedException {

        //given
        ExecutorService executorService = Executors.newFixedThreadPool(100); // 스레드풀에 스레드 100개 준비
        CountDownLatch countDownLatch = new CountDownLatch(100);

        LectureOpenAppRequest lectureOpenReq = LectureOpenAppRequest.builder()
                .title("test")
                .lecturer("test")
                .location("test")
                .capacity(25)
                .startDateTime(LocalDateTime.now())
                .content("test")
                .build();
        Long lectureNo = lectureService.lectureOpen(lectureOpenReq);

        List<String> employeeNoList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            employeeNoList.add(String.format("K%05d", i));
        }

        //when
        for (String employeeNo : employeeNoList) {
            //스레드 n개중 한개의 쓰레드 할당
            executorService.submit(() -> {
                try {
                    ApplyLectureAppRequest applyLectureReq = ApplyLectureAppRequest.builder()
                            .lectureNo(lectureNo)
                            .employeeNo(employeeNo)
                            .build();
                    lectureService.applyLecture(applyLectureReq);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then
        assertThat(lectureService.getEmployees(lectureNo).size()).isEqualTo(lectureOpenReq.getCapacity());
    }

    @Test
    // @Property
    // @DisplayName("수강신청 테스트")
    void 수강신청_테스트(List<String> employeeNoList) throws InterruptedException {

        //given
        ExecutorService executorService = Executors.newFixedThreadPool(100); // 스레드풀에 스레드 100개 준비
        CountDownLatch countDownLatch = new CountDownLatch(100);

        LectureOpenAppRequest lectureOpenReq = LectureOpenAppRequest.builder()
                .title("test")
                .lecturer("test")
                .location("test")
                .capacity(25)
                .startDateTime(LocalDateTime.now())
                .content("test")
                .build();
        Long lectureNo = lectureService.lectureOpen(lectureOpenReq);

        //when
        for (String employeeNo : employeeNoList) {
            //스레드 n개중 한개의 쓰레드 할당
            executorService.submit(() -> {
                try {
                    ApplyLectureAppRequest applyLectureReq = ApplyLectureAppRequest.builder()
                            .lectureNo(lectureNo)
                            .employeeNo(employeeNo)
                            .build();
                    lectureService.applyLecture(applyLectureReq);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then
        assertThat(lectureService.getEmployees(lectureNo)).isEqualTo(lectureOpenReq.getCapacity());
    }

}