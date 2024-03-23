package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {
    @DisplayName("열번호에 맞는 파일을 찾을 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"0, A", "1, B", "2, C", "3, D", "4, E", "5, F", "6, G", "7, H"})
    void should_ReturnAppropriateFile_When_ColumnNumberIsGiven(int columnNumber, File file) {
        assertThat(File.from(columnNumber)).isEqualTo(file);
    }

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
        assertThat(left.isFurtherLeftThan(right)).isTrue();
    }

    @DisplayName("파일이 특정 파일보다 보드 위치에서 더 오른쪽인지 알 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"A, B", "B, E", "D, H", "G, H"})
    void should_CheckIsFileFurtherRightThanTarget(File left, File right) {
        assertThat(right.isFurtherRightThan(left)).isTrue();
    }

    @DisplayName("가중치만큼 움직인 파일을 계산할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"A, 1, B", "A, 2, C", "A, 3, D", "A, 4, E", "A, 5, F"})
    void should_CalculateMovedFile_When_MoveWeightIsGiven(File start, int weight, File moved) {
        assertThat(start.move(weight)).isEqualTo(moved);
    }
}
