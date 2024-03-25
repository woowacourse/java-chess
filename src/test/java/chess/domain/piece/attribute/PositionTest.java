package chess.domain.piece.attribute;

import static org.assertj.core.api.Assertions.assertThat;

import static chess.domain.chessboard.attribute.Direction.DOWN;
import static chess.domain.chessboard.attribute.Direction.DOWN_LEFT;
import static chess.domain.chessboard.attribute.Direction.DOWN_RIGHT;
import static chess.domain.chessboard.attribute.Direction.LEFT;
import static chess.domain.chessboard.attribute.Direction.RIGHT;
import static chess.domain.chessboard.attribute.Direction.UP;
import static chess.domain.chessboard.attribute.Direction.UP_LEFT;
import static chess.domain.chessboard.attribute.Direction.UP_RIGHT;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.attribute.Direction;

class PositionTest {

    private static final String INITIAL_POSITION = "d5";

    static Stream<Arguments> moveTo() {
        return Stream.of(
                Arguments.of(UP, Position.from("d6")),
                Arguments.of(UP_RIGHT, Position.from("e6")),
                Arguments.of(RIGHT, Position.from("e5")),
                Arguments.of(DOWN_RIGHT, Position.from("e4")),
                Arguments.of(DOWN, Position.from("d4")),
                Arguments.of(DOWN_LEFT, Position.from("c4")),
                Arguments.of(LEFT, Position.from("c5")),
                Arguments.of(UP_LEFT, Position.from("c6"))
        );
    }

    @DisplayName("현재 위치에서 주어진 방향으로 이동한다.")
    @MethodSource
    @ParameterizedTest
    void moveTo(Direction direction, Position expected) {
        Position sut = Position.from(INITIAL_POSITION);
        Position actual = sut.moveTo(direction).get();
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> after() {
        return Stream.of(
                Arguments.of(Movement.of(UP, UP_LEFT), Position.from("c7")),
                Arguments.of(Movement.of(UP, UP_RIGHT), Position.from("e7")),
                Arguments.of(Movement.of(RIGHT, UP_RIGHT), Position.from("f6")),
                Arguments.of(Movement.of(RIGHT, DOWN_RIGHT), Position.from("f4")),
                Arguments.of(Movement.of(DOWN, DOWN_LEFT), Position.from("c3")),
                Arguments.of(Movement.of(DOWN, DOWN_RIGHT), Position.from("e3")),
                Arguments.of(Movement.of(LEFT, DOWN_LEFT), Position.from("b4")),
                Arguments.of(Movement.of(LEFT, UP_LEFT), Position.from("b6"))
        );
    }

    @DisplayName("현재 위치에서 주어진 이동을 수행한다.")
    @MethodSource
    @ParameterizedTest
    void after(Movement movement, Position expected) {
        Position sut = Position.from(INITIAL_POSITION);
        Position actual = sut.after(movement).get();
        assertThat(actual).isEqualTo(expected);
    }
}
