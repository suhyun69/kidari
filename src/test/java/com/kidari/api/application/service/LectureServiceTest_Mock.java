package com.kidari.api.application.service;

import com.kidari.api.application.port.out.*;
import com.kidari.api.domain.History;
import lombok.extern.slf4j.Slf4j;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@Slf4j
@SpringBootTest
public class LectureServiceTest_Mock {

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

    @Property
    void getPopularLectures(@ForAll @Size(value = 5) @UniqueElements List<@IntRange(min = 1, max=10) Integer> sample) {

        // given
        List<History> testCases = new ArrayList<>();
        for(Integer s : sample) {
            History history = History.builder()
                    .lectureNo(Long.valueOf(s))
                    .build();
            for(int i=0; i<s ; i++) {
                testCases.add(history);
            }

            /*
                ex)
                sample = [ 1, 5, 3, 4, 2] // lectureNo
                testCases = [ 1, 5, 5, 5, 5, 5, 3, 3, 3, 4, 4, 4, 4, 2, 2]
             */
        }
        given(getHistoryPort.getHistoriesAfter3DaysBefore()).willReturn(testCases);

        List<Long> expected = sample.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .map(s -> Long.valueOf(s))
                .collect(Collectors.toList());

        // then
        assertThat(lectureService.getPopularLectures()).isEqualTo(expected);

    }
}