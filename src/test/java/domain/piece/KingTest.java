package domain.piece;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("모든 방향으로 한 칸씩 킹 이동 가능")
    void canMove(Position source, Position target) {
        King king = new King(Color.WHITE);
        assertThat(king.canMove(source, target)).isTrue();
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
    @DisplayName("1칸을 초과하면 킹 이동 불가")
    void cannotMove() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 3);
        assertThat(king.canMove(position1, position2)).isFalse();
    }

    @Test
    @DisplayName("Rank가 같고 File 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameRank() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(2, 1);
        assertThat(king.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같고 Rank 차이가 한 칸이면 킹 이동 가능")
    void canMove_SameFile() {
        King king = new King(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 2);
        assertThat(king.canMove(position1, position2)).isTrue();
    }
}
