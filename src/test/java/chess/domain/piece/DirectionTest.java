package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DirectionTest {
    @ParameterizedTest
    @DisplayName("방향을 입력한 경우 정상적으로 반환")
    @MethodSource("provideAllDirections")
    void of(Direction actual, Direction expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("직선 방향이 아닌 경우 예외 발생")
    void ofLinear_invalid() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofLinear(1, 1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofLinear(-1, -1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofLinear(1, -1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofLinear(-1, 1));
    }

    @Test
    @DisplayName("직선 방향을 입력한 경우 정상적으로 반환")
    void ofLinear() {
        assertThat(Direction.ofLinear(1, 0)).isEqualTo(Direction.EAST);
        assertThat(Direction.ofLinear(0, 1)).isEqualTo(Direction.NORTH);
        assertThat(Direction.ofLinear(-1, 0)).isEqualTo(Direction.WEST);
        assertThat(Direction.ofLinear(0, -1)).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("대각선 방향이 아닌 경우 예외 발생")
    void ofDiagonal_invalid() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofDiagonal(1, 0));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofDiagonal(0, 1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofDiagonal(-1, 0));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofDiagonal(0, -1));
    }

    @Test
    @DisplayName("대각선 방향을 입력한 경우 정상적으로 반환")
    void ofDiagonal() {
        assertThat(Direction.ofDiagonal(1, 1)).isEqualTo(Direction.NE);
        assertThat(Direction.ofDiagonal(-1, -1)).isEqualTo(Direction.SW);
        assertThat(Direction.ofDiagonal(1, -1)).isEqualTo(Direction.SE);
        assertThat(Direction.ofDiagonal(-1, 1)).isEqualTo(Direction.NW);
    }

    @Test
    @DisplayName("8방향을 입력한 경우 정상적으로 반환")
    void ofEvery() {
        assertThat(Direction.ofEvery(1, 1)).isEqualTo(Direction.NE);
        assertThat(Direction.ofEvery(-1, -1)).isEqualTo(Direction.SW);
        assertThat(Direction.ofEvery(1, -1)).isEqualTo(Direction.SE);
        assertThat(Direction.ofEvery(-1, 1)).isEqualTo(Direction.NW);
        assertThat(Direction.ofEvery(1, 0)).isEqualTo(Direction.EAST);
        assertThat(Direction.ofEvery(0, 1)).isEqualTo(Direction.NORTH);
        assertThat(Direction.ofEvery(-1, 0)).isEqualTo(Direction.WEST);
        assertThat(Direction.ofEvery(0, -1)).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("8방향아 아닌 경우 예외 발생")
    void ofEvery_invalid() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(1, 2));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(1, -2));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(2, 1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(2, -1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(-1, -2));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(-1, 2));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(-2, -1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Direction.ofEvery(-2, 1));
    }

    private static Stream<Arguments> provideAllDirections() {
        return Stream.of(
            Arguments.of(Direction.of(0, 1), Direction.NORTH),
            Arguments.of(Direction.of(0, -1), Direction.SOUTH),
            Arguments.of(Direction.of(1, 0), Direction.EAST),
            Arguments.of(Direction.of(-1, 0), Direction.WEST),
            Arguments.of(Direction.of(1, 1), Direction.NE),
            Arguments.of(Direction.of(-1, 1), Direction.NW),
            Arguments.of(Direction.of(1, -1), Direction.SE),
            Arguments.of(Direction.of(-1, -1), Direction.SW),
            Arguments.of(Direction.of(1, 2), Direction.NNE),
            Arguments.of(Direction.of(-1, 2), Direction.NNW),
            Arguments.of(Direction.of(1, -2), Direction.SSE),
            Arguments.of(Direction.of(-1, -2), Direction.SSW),
            Arguments.of(Direction.of(2, 1), Direction.EEN),
            Arguments.of(Direction.of(2, -1), Direction.EES),
            Arguments.of(Direction.of(-2, 1), Direction.WWN),
            Arguments.of(Direction.of(-2, -1), Direction.WWS)
        );
    }
}
