package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.unified.Queen;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("퀸은 직선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveUnlessStraightOrDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveStraightWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveDiagonalWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("퀸은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isTrue();
    }

    @DisplayName("퀸은 직선 또는 대각선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackUnlessStraightOrDiagonal() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackStraightWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackDiagonalWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }
}
