package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.File.D;
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
}
