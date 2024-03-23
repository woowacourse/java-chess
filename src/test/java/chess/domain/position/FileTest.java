package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {
    @DisplayName("체스 파일 간 거리를 계산할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"A, B, 1", "H, A, 7", "C, E, 2"})
    void should_CalculateDistance_When_OtherFileIsGiven(File file1, File file2, int distance) {
        assertAll(
                () -> assertThat(file1.calculateDistanceWith(file2)).isEqualTo(distance),
                () -> assertThat(file1.calculateDistanceWith(file2)).isEqualTo(distance)
        );
    }
}
