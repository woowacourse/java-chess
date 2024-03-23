package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = PositionFixture.A4;
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                PositionFixture.A5, Pawn.WHITE,
                PositionFixture.B6, Queen.WHITE,
                PositionFixture.C3, Queen.WHITE
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.C5, PositionFixture.B2));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Knight knight = Knight.WHITE;
        Position currentKingPosition = PositionFixture.A4;
        Map<Position, Piece> board = Map.of(currentKingPosition, knight,
                PositionFixture.A5, Pawn.BLACK,
                PositionFixture.B6, Queen.WHITE,
                PositionFixture.C3, Queen.BLACK
        );

        Set<Position> movablePositions = knight.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.C5, PositionFixture.B2, PositionFixture.C3));
    }
}
