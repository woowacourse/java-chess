package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetTeamMThenStop() {
        Queen queen = Queen.WHITE;
        Position currentQueenPosition = PositionFixture.G7;
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                PositionFixture.E7, Pawn.WHITE,
                PositionFixture.F6, Pawn.WHITE,
                PositionFixture.G5, Pawn.WHITE
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.G6,
                        PositionFixture.G8,
                        PositionFixture.H6,
                        PositionFixture.H7,
                        PositionFixture.H8,
                        PositionFixture.F8,
                        PositionFixture.F7));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenQueenMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Queen queen = Queen.WHITE;
        Position currentQueenPosition = PositionFixture.G7;
        Map<Position, Piece> board = Map.of(
                currentQueenPosition, queen,
                PositionFixture.E7, Pawn.BLACK,
                PositionFixture.F6, Pawn.BLACK,
                PositionFixture.G5, Pawn.BLACK
        );

        Set<Position> movablePositions = queen.calculateMovablePositions(currentQueenPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.G6,
                        PositionFixture.G8,
                        PositionFixture.H6,
                        PositionFixture.H7,
                        PositionFixture.H8,
                        PositionFixture.F8,
                        PositionFixture.F7,
                        PositionFixture.E7,
                        PositionFixture.F6,
                        PositionFixture.G5));
    }
}
