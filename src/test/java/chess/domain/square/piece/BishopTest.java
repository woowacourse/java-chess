package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Bishop;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍은 대각선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("비숍은 대각선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveUnlessDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        Piece obstacle = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("비숍은 대각선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canMove = attackerPiece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }
}
