package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static chess.domain.File.*;
import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @ParameterizedTest
    @CsvSource({"A, 3", "B, 2", "D, 0", "H, 4"})
    @DisplayName("거리 계산 테스트")
    void calculateDistanceTest(final File otherFile, final int expectedDistance) {
        final File file = D;

        final int actualDistance = file.calculateDistance(otherFile);

        assertThat(actualDistance).isEqualTo(expectedDistance);
    }

    @Nested
    @DisplayName("두 파일 사이의 파일들을 구한다")
    class GetFilesToTest {

        @Test
        @DisplayName("오름차순 파일들을 반환한다")
        void get_ascending_test() {
            final File startFile = A;
            final File endFile = H;

            final List<File> betweenFiles = startFile.getFilesTo(endFile);

            assertThat(betweenFiles).containsExactly(B, C, D, E, F, G);
        }

        @Test
        @DisplayName("내림차순 파일들을 반환한다")
        void get_descending_test() {
            final File startFile = H;
            final File endFile = A;

            final List<File> betweenFiles = startFile.getFilesTo(endFile);

            assertThat(betweenFiles).containsExactly(G, F, E, D, C, B);
        }
    }
}
