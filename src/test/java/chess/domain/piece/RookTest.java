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

class RookTest {
    @DisplayName("룩은 직선으로 움직일 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"4,1", "4,2", "4,3", "4,5", "4,6", "4,7", "4,8",
            "1,4", "2,4", "3,4", "5,4", "6,4", "7,4", "8,4"})
    void rookIsMovable(int row, int column) {
        Piece rook = new Rook(Team.WHITE);

        boolean movable = rook.isMovable(new Movement(
                Position.of(4, 4), Position.of(row, column)));

        assertThat(movable).isTrue();
    }

    @DisplayName("룩은 직선이 아닌 경우, 움직일 수 없다.")
    @Test
    void rookMoveOverLine() {
        Piece rook = new Rook(Team.WHITE);

        boolean movable = rook.isMovable(new Movement(
                Position.of(4, 4), Position.of(3, 3)));

        assertThat(movable).isFalse();
    }

    @DisplayName("두 위치 사이의 룩이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        Piece rook = new Rook(Team.WHITE);

        Set<Position> betweenPositions = rook.findBetweenPositions(new Movement(
                Position.of(4, 4), Position.of(4, 7)));
        Set<Position> expectedBetweenPositions = Set.of(Position.of(4, 5), Position.of(4, 6));

        assertThat(betweenPositions).isEqualTo(expectedBetweenPositions);
    }
}
