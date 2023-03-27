package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileTest {

    @ParameterizedTest
    @MethodSource("getValueDiffTestCases")
    @DisplayName("대상 File과의 값 차이를 반환한다.")
    void getValueDiff(File file, File targetFile, int expectedValueDiff) {
        // when, then
        assertThat(file.getValueDiff(targetFile)).isEqualTo(expectedValueDiff);
    }

    static Stream<Arguments> getValueDiffTestCases() {
        return Stream.of(
                Arguments.arguments(File.A, File.A, 0),
                Arguments.arguments(File.A, File.B, 1),
                Arguments.arguments(File.A, File.C, 2),
                Arguments.arguments(File.A, File.D, 3),
                Arguments.arguments(File.A, File.E, 4),
                Arguments.arguments(File.A, File.F, 5),
                Arguments.arguments(File.A, File.G, 6),
                Arguments.arguments(File.A, File.H, 7)
        );
    }

    @ParameterizedTest
    @MethodSource("getValuePointTestCases")
    @DisplayName("대상 File과의 값 차이에 따른 수평 좌표 값을 반환한다.")
    void getValuePoint(File file, File targetFile, int expectedValuePoint) {
        // when, then
        assertThat(file.getValuePoint(targetFile)).isEqualTo(expectedValuePoint);
    }

    static Stream<Arguments> getValuePointTestCases() {
        return Stream.of(
                Arguments.arguments(File.D, File.D, 0),
                Arguments.arguments(File.D, File.E, 1),
                Arguments.arguments(File.D, File.F, 1),
                Arguments.arguments(File.D, File.G, 1),
                Arguments.arguments(File.D, File.H, 1),
                Arguments.arguments(File.D, File.C, -1),
                Arguments.arguments(File.D, File.B, -1),
                Arguments.arguments(File.D, File.A, -1)
        );
    }
}
