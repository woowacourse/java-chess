package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetTeamMThenStop() {
        Rook rook = Rook.WHITE;
        Position currentRookPosition = PositionFixture.A1;
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                PositionFixture.A4, Rook.WHITE,
                PositionFixture.E1, Bishop.WHITE
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.A2, PositionFixture.A3, PositionFixture.B1, PositionFixture.C1,
                        PositionFixture.D1));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Rook rook = Rook.WHITE;
        Position currentRookPosition = PositionFixture.H8;
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                PositionFixture.G8, Rook.BLACK,
                PositionFixture.H5, Knight.BLACK
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.H7, PositionFixture.H6, PositionFixture.H5, PositionFixture.G8));
    }
}
