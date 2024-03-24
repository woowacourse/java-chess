package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @DisplayName("좌표에 해당하는 Position 객체를 생성한다")
    @ParameterizedTest
    @ValueSource(strings = {"a1", "h8"})
    void createValidPosition(String coordinate) {
        assertThatCode(() -> Position.from(coordinate))
            .doesNotThrowAnyException();
    }

    @DisplayName("좌표 표현 범위를 벗어나면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"i6", "a0", "g9", "a"})
    void createInvalidPosition(String coordinate) {
        assertThatThrownBy(() -> Position.from(coordinate))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("문자열 좌표를 받아 Position 객체를 생성한다")
    @ParameterizedTest
    @MethodSource("provideValidCoordinate")
    void createValidPositionWithCoordinate(String coordinate, Position expected) {
        assertThat(Position.from(coordinate)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideValidCoordinate() {
        return Stream.of(
            Arguments.of("b2", new Position(Column.B, Row.TWO)),
            Arguments.of("h8", new Position(Column.H, Row.EIGHT)),
            Arguments.of("a1", new Position(Column.A, Row.ONE))
        );
    }
}
