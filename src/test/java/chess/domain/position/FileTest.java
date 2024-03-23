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

    @DisplayName("파일이 특정 파일보다 보드 위치에서 더 왼쪽인지 알 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"A, B", "B, E", "D, H", "G, H"})
    void should_CheckIsFileFurtherLeftThanTarget(File left, File right) {
        assertThat(left.isFurtherLeftThan(right));
    }

    @DisplayName("파일이 특정 파일보다 보드 위치에서 더 오른쪽인지 알 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"A, B", "B, E", "D, H", "G, H"})
    void should_CheckIsFileFurtherRightThanTarget(File left, File right) {
        assertThat(right.isFurtherLeftThan(left));
    }
}
