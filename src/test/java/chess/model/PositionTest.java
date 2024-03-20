package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @DisplayName("x와 y좌표에 해당하는 Position 객체를 생성한다")
    @Test
    void createValidPosition() {
        assertThatCode(() -> new Position(3, 6))
                .doesNotThrowAnyException();
    }

    @DisplayName("0 이상 7 이하의 좌표 값이 아니면 예외가 발생한다")
    @ParameterizedTest
    @MethodSource("provideInvalidPosition")
    void createInvalidPosition(int x, int y) {
        assertThatThrownBy(() -> new Position(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> provideInvalidPosition() {
        return Stream.of(
                Arguments.of(-1, 6),
                Arguments.of(3, 8)
        );
    }

    @DisplayName("문자열 좌표를 받아 Position 객체를 생성한다")
    @ParameterizedTest
    @MethodSource("provideValidCoordinate")
    void createValidPositionWithCoordinate(String coordinate, Position expected) {
        assertThat(Position.from(coordinate)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideValidCoordinate() {
        return Stream.of(
                Arguments.of("b2", new Position(6, 1)),
                Arguments.of("h8", new Position(0, 7)),
                Arguments.of("a1", new Position(7, 0))
        );
    }
}
