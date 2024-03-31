package com.kidari.api.application.service;

import com.kidari.api.application.port.out.*;
import net.bytebuddy.asm.Advice;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

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

    @ParameterizedTest
    @MethodSource("testCases")
    void isAvailable_parameterized(Integer diff, Boolean expected) {

        assertThat(lectureService.isAvailable(LocalDateTime.now().plusDays(diff), LocalDateTime.now()))
                .isEqualTo(expected);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                of(-10, false),
                of(-9, false),
                of(-8, false),
                of(-7, false),
                of(-6, false),
                of(-5, false),
                of(-4, false),
                of(-3, false),
                of(-2, false),
                of(-1, true),
                of(0, true),
                of(1, true),
                of(2, true),
                of(3, true),
                of(4, true),
                of(5, true),
                of(6, true),
                of(7, true),
                of(8, false),
                of(9, false),
                of(10, false)
        );
    }
}