package domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Direction은 ")
class DirectionTest {

    @ParameterizedTest
    @MethodSource("crossLocatedPositionCase")
    @DisplayName("두 Position이 상하좌우 방향에 위치하면 CROSS다.")
    void isCrossTest(Position end) {
        // given
        Position start = Position.of(4, 4);

        // when
        Direction direction = Direction.of(start, end);

        // then
        assertThat(direction).isEqualTo(Direction.CROSS);
    }

    @ParameterizedTest
    @MethodSource("diagonalLocatedPositionCase")
    @DisplayName("두 Position이 대각선 방향에 위치하면 DIAGONAL이다.")
    void isDiagonalTest(Position end) {
        // given
        Position start = Position.of(4, 4);

        // when
        Direction direction = Direction.of(start, end);

        // then
        assertThat(direction).isEqualTo(Direction.DIAGONAL);
    }

    @Test
    @DisplayName("두 Position이 상하좌우 또는 대각선 방향에 위치해 있지 않으면 OTHER다.")
    void isOtherTest() {
        // given
        Position start = Position.of(4, 4);
        Position end = Position.of(1, 2);

        // when
        Direction direction = Direction.of(start, end);

        // then
        assertThat(direction).isEqualTo(Direction.OTHER);

    }

    static Stream<Arguments> crossLocatedPositionCase() {
        return Stream.of(
                Arguments.of(Position.of(6, 4)),
                Arguments.of(Position.of(2, 4)),
                Arguments.of(Position.of(4, 6)),
                Arguments.of(Position.of(4, 2))
        );
    }

    static Stream<Arguments> diagonalLocatedPositionCase() {
        return Stream.of(
                Arguments.of(Position.of(6, 6)),
                Arguments.of(Position.of(2, 2)),
                Arguments.of(Position.of(2, 6)),
                Arguments.of(Position.of(6, 2))
        );
    }
}
