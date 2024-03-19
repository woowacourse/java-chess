package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThatCode(() -> new Rook(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    public void givenRookMoveWhenMeetTeamMThenStop() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = new Position(File.a, Rank.ONE);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                new Position(File.a, Rank.FOUR), new Bishop(Color.WHITE),
                new Position(File.e, Rank.ONE), new Knight(Color.WHITE)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.a, Rank.TWO), new Position(File.a, Rank.THREE), new Position(File.b, Rank.ONE),
                        new Position(File.c, Rank.ONE), new Position(File.d, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    public void givenRookMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        Rook rook = new Rook(Color.WHITE);
        Position currentRookPosition = new Position(File.h, Rank.EIGHT);
        Map<Position, Piece> board = Map.of(
                currentRookPosition, rook,
                new Position(File.g, Rank.EIGHT), new Bishop(Color.BLACK),
                new Position(File.h, Rank.FIVE), new Knight(Color.BLACK)
        );

        Set<Position> movablePositions = rook.calculateMovablePositions(currentRookPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.g, Rank.EIGHT), new Position(File.h, Rank.FIVE),
                        new Position(File.h, Rank.SIX), new Position(File.h, Rank.SEVEN))
        );
    }
}
