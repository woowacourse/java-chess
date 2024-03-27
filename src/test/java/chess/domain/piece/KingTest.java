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

class KingTest {
    @DisplayName("킹은 한칸 내 전범위로 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "1,2", "1,3", "2,1", "2,3", "3,1", "3,2", "3,3"})
    void kingIsMovable(int row, int column) {
        Piece king = new King(Team.WHITE);

        boolean movable = king.isMovable(new Movement(
                Position.of(2, 2), Position.of(row, column)));

        assertThat(movable).isTrue();
    }

    @DisplayName("킹은 한칸 초과하여 이동할 수 없다.")
    @Test
    void kingIsNotMovable() {
        Piece king = new King(Team.WHITE);

        boolean movable = king.isMovable(new Movement(
                Position.of(1, 1), Position.of(3, 3)));

        assertThat(movable).isFalse();
    }

    @DisplayName("두 위치 사이의 킹이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        Piece king = new King(Team.WHITE);

        Set<Position> betweenPositions = king.findBetweenPositions(new Movement(
                Position.of(3, 3), Position.of(2, 2)));

        assertThat(betweenPositions).isEmpty();
    }
}
