package domain.piece;


import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("두 칸 전진 후 대각선 방향으로 나이트 이동 가능")
    void canMove(Position source, Position target) {
        Knight knight = new Knight(Color.WHITE);
        assertThat(knight.canMove(source, target)).isTrue();
    }

    private static Stream<Arguments> canMoveDirection() {
        return Stream.of(
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_UP_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_UP_RIGHT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_DOWN_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_DOWN_RIGHT),
                Arguments.of(PositionArgument.FROM, PositionArgument.LEFT_LEFT_DOWN),
                Arguments.of(PositionArgument.FROM, PositionArgument.LEFT_LEFT_UP),
                Arguments.of(PositionArgument.FROM, PositionArgument.RIGHT_RIGHT_DOWN),
                Arguments.of(PositionArgument.FROM, PositionArgument.RIGHT_RIGHT_UP)
        );
    }

    @Test
    @DisplayName("나이트 이동 불가")
    void cannotMove() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(4, 4);
        Position position2 = Position.of(3, 3);
        assertThat(knight.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("같은 위치로는 이동 불가")
    void cannotMove_SamePosition() {
        Knight knight = new Knight(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 1);
        assertThat(knight.canMove(position1, position2)).isFalse();
    }
}
