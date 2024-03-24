package domain.piece;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("모든 대각선으로 비숍 이동 가능")
    void canMove(Position source, Position target) {
        Bishop bishop = new Bishop(Color.WHITE);
        assertThat(bishop.canMove(source, target)).isTrue();
    }

    private static Stream<Arguments> canMoveDirection() {
        return Stream.of(
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_RIGHT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_RIGHT)
        );
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 비숍 이동 불가")
    void cannotMove() {
        Bishop bishop = new Bishop(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(7, 8);
        assertThat(bishop.canMove(position1, position2)).isFalse();
    }
}
