package domain.piece;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("모든 직선 방향으로 룩 이동 가능")
    void canMove(Position source, Position target) {
        Rook rook = new Rook(Color.WHITE);
        assertThat(rook.canMove(source, target)).isTrue();
    }

    private static Stream<Arguments> canMoveDirection() {
        return Stream.of(
                Arguments.of(PositionArgument.from, PositionArgument.UP),
                Arguments.of(PositionArgument.from, PositionArgument.DOWN),
                Arguments.of(PositionArgument.from, PositionArgument.LEFT),
                Arguments.of(PositionArgument.from, PositionArgument.RIGHT)
        );
    }

    @Test
    @DisplayName("Rank가 같으면 룩 이동 가능")
    void canMove_SameRank() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 1);
        assertThat(rook.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("File이 같으면 룩 이동 가능")
    void canMove_SameFile() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(1, 8);
        assertThat(rook.canMove(position1, position2)).isTrue();
    }

    @Test
    @DisplayName("Rank와 File이 모두 다르면 룩 이동 불가")
    void cannotMove_DifferentFileDifferentRank() {
        Rook rook = new Rook(Color.WHITE);
        Position position1 = Position.of(1, 1);
        Position position2 = Position.of(8, 8);
        assertThat(rook.canMove(position1, position2)).isFalse();
    }
}
