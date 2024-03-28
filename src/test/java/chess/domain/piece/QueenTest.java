package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    @DisplayName("퀸은 직선 혹은 대각선으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8", "1,7", "2,6", "3,5", "5,3", "6,2", "7,1",
            "4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8", "1,4", "2,4", "3,4", "5,4", "6,4", "7,4", "8,4"})
    void queenIsMovable(int row, int column) {
        Piece queen = new Queen(Team.WHITE);

        boolean movable = queen.isMovable(new Movement(
                Position.of(4, 4), Position.of(row, column)));

        assertThat(movable).isTrue();
    }

    @DisplayName("퀸은 직선 혹은 대각선이 아닌 경우, 움직일 수 없다.")
    @Test
    void queenIsNotMovable() {
        Piece queen = new Queen(Team.WHITE);

        boolean movable = queen.isMovable(new Movement(
                Position.of(4, 4), Position.of(2, 3)));

        assertThat(movable).isFalse();
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonal() {
        Piece queen = new Queen(Team.WHITE);

        Set<Position> betweenPositions = queen.findBetweenPositions(new Movement(
                Position.of(4, 4), Position.of(7, 1)));
        Set<Position> expectedBetweenPositions = Set.of(Position.of(5, 3), Position.of(6, 2));

        assertThat(betweenPositions).isEqualTo(expectedBetweenPositions);
    }
}
