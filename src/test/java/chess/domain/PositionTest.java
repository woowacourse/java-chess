package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Direction;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @ParameterizedTest
    @MethodSource("getMovementResult")
    @DisplayName("방향에 맞게 이동한다.")
    void moveTowardDirection(Position sourcePosition, Direction direction, Position expectedTargetPosition) {
        Position actualTargetPosition = sourcePosition.moveTowardDirection(direction);

        assertThat(actualTargetPosition).isEqualTo(expectedTargetPosition);
    }

    static Stream<Arguments> getMovementResult() {
        return Stream.of(
                Arguments.of(Position.of('d', 5), Direction.UP, Position.of('d', 6)),
                Arguments.of(Position.of('d', 5), Direction.DOWN, Position.of('d', 4)),
                Arguments.of(Position.of('d', 5), Direction.LEFT, Position.of('c', 5)),
                Arguments.of(Position.of('d', 5), Direction.RIGHT, Position.of('e', 5)),
                Arguments.of(Position.of('d', 5), Direction.LEFT_UP, Position.of('c', 6)),
                Arguments.of(Position.of('d', 5), Direction.LEFT_DOWN, Position.of('c', 4)),
                Arguments.of(Position.of('d', 5), Direction.RIGHT_UP, Position.of('e', 6)),
                Arguments.of(Position.of('d', 5), Direction.RIGHT_DOWN, Position.of('e', 4)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_LEFT_UP, Position.of('b', 6)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_LEFT_DOWN, Position.of('b', 4)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_RIGHT_UP, Position.of('f', 6)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_RIGHT_DOWN, Position.of('f', 4)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_UP_LEFT, Position.of('c', 7)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_UP_RIGHT, Position.of('e', 7)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_DOWN_LEFT, Position.of('c', 3)),
                Arguments.of(Position.of('d', 5), Direction.KNIGHT_DOWN_RIGHT, Position.of('e', 3))
        );
    }
}
