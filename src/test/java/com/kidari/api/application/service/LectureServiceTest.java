package com.kidari.api.application.service;

import com.kidari.api.application.port.in.command.ApplyLectureAppRequest;
import com.kidari.api.application.port.in.command.LectureOpenAppRequest;
import com.kidari.api.application.port.out.*;
import com.kidari.api.domain.Lecture;
import lombok.extern.slf4j.Slf4j;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@SpringBootTest
public class LectureServiceTest {

    private final AddLecturePort addLecturePort = Mockito.mock(AddLecturePort.class);
    private final GetLecturePort getLecturePort = Mockito.mock(GetLecturePort.class);
    private final AddHistoryPort addHistoryPort = Mockito.mock(AddHistoryPort.class);
    private final DeleteHistoryPort deleteHistoryPort = Mockito.mock(DeleteHistoryPort.class);
    private final GetHistoryPort getHistoryPort = Mockito.mock(GetHistoryPort.class);
    private final LectureService lectureService = new LectureService(addLecturePort, getLecturePort, addHistoryPort, deleteHistoryPort, getHistoryPort);

    @Property(shrinking = ShrinkingMode.FULL)
    void isAvailable(@ForAll @Size(value = 100) List<@IntRange(min = -10, max=10) Integer> diffs) {

        for(Integer diff : diffs) {
            LocalDateTime now = LocalDateTime.now();
            Boolean expected = -1 <= diff && diff <= 7;

            /*
            Boolean result = lectureService.isAvailable(now.plusDays(diff), now);
            if(result != expected) {
                int a = 1;
            }
            */

            assertThat(lectureService.isAvailable(now.plusDays(diff), now))
                    .isEqualTo(expected);
        }
    }
}