package com.kidari.api.application.service;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class LectureServiceTest_Real {

    @Autowired
    private LectureService lectureService;

    // @Test
    @Property
    // @DisplayName("수강신청 테스트")
    void 수강신청_테스트(@ForAll @Size(value = 100) List<@AlphaChars @StringLength(min = 5, max=5) String> employeeNoList) throws InterruptedException {

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