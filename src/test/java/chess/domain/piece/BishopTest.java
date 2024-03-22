package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetTeamMThenStop() {
        Bishop bishop = Bishop.WHITE;
        Position currentBishopPosition = PositionFixture.A4;
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                PositionFixture.B3, Bishop.WHITE,
                PositionFixture.C6, Knight.WHITE
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.B5));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Bishop bishop = Bishop.WHITE;
        Position currentBishopPosition = PositionFixture.A4;
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                PositionFixture.B3, Bishop.BLACK,
                PositionFixture.C6, Knight.BLACK
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.B5, PositionFixture.C6, PositionFixture.B3));
    }
}
