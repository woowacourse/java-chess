package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationTest {

    @Test
    @DisplayName("String으로 주어진 위치 값을 Location으로 반환")
    void stringToLocation() {
        assertThat(Location.of("a1")).isEqualTo(Location.of(File.A, Rank.ONE));
    }

    @ParameterizedTest
    @DisplayName("범위에서 벗어난 String으로 Location을 요청할 때 예외 발생")
    @MethodSource("stringLocationParameter")
    void stringToLocationException(String location) {
        assertThatThrownBy(() -> Location.of(location))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> stringLocationParameter() {
        return Stream.of(
                Arguments.arguments("a0"),
                Arguments.arguments("g0"),
                Arguments.arguments("A1"),
                Arguments.arguments("i9"),
                Arguments.arguments("i8"),
                Arguments.arguments("z8"),
                Arguments.arguments("a10"),
                Arguments.arguments("aa"),
                Arguments.arguments("11")
        );
    }

    @ParameterizedTest
    @DisplayName("file 좌표 차이를 확인한다.")
    @MethodSource("fileDiffParameter")
    void fileDiff(File source, File target, int diff) {
        assertThat(target.computeDiff(source)).isEqualTo(diff);
    }

    public static Stream<Arguments> fileDiffParameter() {
        return Stream.of(
                Arguments.arguments(File.A, File.C, -2),
                Arguments.arguments(File.A, File.A, 0),
                Arguments.arguments(File.D, File.C, 1)
        );
    }

    @ParameterizedTest
    @DisplayName("rank 좌표 차이를 확인한다.")
    @MethodSource("rankDiffParameter")
    void rankDiff(Rank source, Rank target, int diff) {
        assertThat(target.computeDiff(source)).isEqualTo(diff);
    }

    public static Stream<Arguments> rankDiffParameter() {
        return Stream.of(
                Arguments.arguments(Rank.ONE, Rank.THREE, -2),
                Arguments.arguments(Rank.THREE, Rank.ONE, 2),
                Arguments.arguments(Rank.EIGHT, Rank.EIGHT, 0)
        );
    }
}