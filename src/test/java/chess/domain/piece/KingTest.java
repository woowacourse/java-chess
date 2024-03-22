package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        King king = King.WHITE;
        Position currentKingPosition = PositionFixture.E1;
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                PositionFixture.F2, Pawn.WHITE,
                PositionFixture.D1, Queen.WHITE);

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.D2, PositionFixture.E2, PositionFixture.F1));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        King king = King.WHITE;
        Position currentKingPosition = PositionFixture.E1;
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                PositionFixture.F2, Pawn.WHITE,
                PositionFixture.D1, Queen.WHITE,
                PositionFixture.D2, Pawn.BLACK);

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.D2, PositionFixture.E2, PositionFixture.F1));
    }
}
