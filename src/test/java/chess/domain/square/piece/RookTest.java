package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Rook;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {
    @DisplayName("룩은 직선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("룩은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.B), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotMoveWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        Piece obstacle = Rook.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }
}
