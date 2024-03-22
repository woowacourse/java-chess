package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetTeamMThenStop() {
        Bishop bishop = Bishop.WHITE;
        Position currentBishopPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                Position.of(File.B, Rank.THREE), Bishop.WHITE,
                Position.of(File.C, Rank.SIX), Knight.WHITE
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.B, Rank.FIVE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenBishopMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Bishop bishop = Bishop.WHITE;
        Position currentBishopPosition = Position.of(File.A, Rank.FOUR);
        Map<Position, Piece> board = Map.of(
                currentBishopPosition, bishop,
                Position.of(File.B, Rank.THREE), Bishop.BLACK,
                Position.of(File.C, Rank.SIX), Knight.BLACK
        );

        Set<Position> movablePositions = bishop.calculateMovablePositions(currentBishopPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.B, Rank.FIVE), Position.of(File.C, Rank.SIX),
                        Position.of(File.B, Rank.THREE)));
    }
}
