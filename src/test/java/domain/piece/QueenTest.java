package domain.piece;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("모든 방향으로 퀸 이동 가능")
    void canMove(Position source, Position target) {
        Queen queen = new Queen(Color.WHITE);
        assertThat(queen.canMove(source, target)).isTrue();
    }

    private static Stream<Arguments> canMoveDirection() {
        return Stream.of(
                Arguments.of(PositionArgument.from, PositionArgument.UP),
                Arguments.of(PositionArgument.from, PositionArgument.DOWN),
                Arguments.of(PositionArgument.from, PositionArgument.LEFT),
                Arguments.of(PositionArgument.from, PositionArgument.RIGHT),
                Arguments.of(PositionArgument.from, PositionArgument.UP_LEFT),
                Arguments.of(PositionArgument.from, PositionArgument.UP_RIGHT),
                Arguments.of(PositionArgument.from, PositionArgument.DOWN_LEFT),
                Arguments.of(PositionArgument.from, PositionArgument.DOWN_RIGHT)
        );
    }

    @Test
    @DisplayName("같은 대각선상이 아니면 퀸 이동 불가")
    void cannotMove() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(7, 8);
        assertThat(queen.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같으면 퀸 이동 가능")
    void canMove_SameRank() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 1);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같으면 퀸 이동 가능")
    void canMove_SameFile() {
        Queen queen = new Queen(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 8);
        assertThat(queen.canMove(position1, position2)).isTrue();
    }
}
