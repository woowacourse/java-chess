package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetTeamMThenStop() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = Position.from(File.A, Rank.ONE);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                Position.from(File.A, Rank.FOUR), new Bishop(Color.WHITE),
                Position.from(File.E, Rank.ONE), new Knight(Color.WHITE)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.A, Rank.TWO), Position.from(File.A, Rank.THREE),
                        Position.from(File.B, Rank.ONE),
                        Position.from(File.C, Rank.ONE), Position.from(File.D, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = Position.from(File.H, Rank.EIGHT);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                Position.from(File.G, Rank.EIGHT), new Bishop(Color.BLACK),
                Position.from(File.H, Rank.FIVE), new Knight(Color.BLACK)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.G, Rank.EIGHT), Position.from(File.H, Rank.FIVE),
                        Position.from(File.H, Rank.SIX), Position.from(File.H, Rank.SEVEN))
        );
    }
}
