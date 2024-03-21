package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Rook(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetTeamMThenStop() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = new Position(File.A, Rank.ONE);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                new Position(File.A, Rank.FOUR), new Bishop(Color.WHITE),
                new Position(File.E, Rank.ONE), new Knight(Color.WHITE)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE), new Position(File.B, Rank.ONE),
                        new Position(File.C, Rank.ONE), new Position(File.D, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenRookMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = new Position(File.H, Rank.EIGHT);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                new Position(File.G, Rank.EIGHT), new Bishop(Color.BLACK),
                new Position(File.H, Rank.FIVE), new Knight(Color.BLACK)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.G, Rank.EIGHT), new Position(File.H, Rank.FIVE),
                        new Position(File.H, Rank.SIX), new Position(File.H, Rank.SEVEN))
        );
    }
}
