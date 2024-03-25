package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.King;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 한 칸 짜리 직선 경로이면 움직일 수 있다.")
    @Test
    void canStraightMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 대각선 경로이면 움직일 수 있다.")
    @Test
    void canDiagonalMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = King.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("킹은 한 칸 짜리 직선 경로이면 공격할 수 있다.")
    @Test
    void canStraightAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = King.from(Color.WHITE);
        Piece attackedPiece = King.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 대각선 경로이면 공격할 수 있다.")
    @Test
    void canDiagonalAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = King.from(Color.WHITE);
        Piece attackedPiece = King.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.B), attackedPiece);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("킹은 한 칸 짜리 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = King.from(Color.WHITE);
        Piece attackedPiece = King.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.A), attackedPiece);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }
}
