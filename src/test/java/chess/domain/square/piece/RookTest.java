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
    void canMoveStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveUnlessStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Rook.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        final boolean canMove = piece.canArrive(pathFinder, board);

        assertThat(canMove).isFalse();
    }

    @DisplayName("룩은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isTrue();
    }

    @DisplayName("룩은 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackUnlessStraight() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.B), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackWithObstacle() {
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Rook.from(Color.WHITE);
        Piece attackedPiece = Rook.from(Color.BLACK);
        Piece obstacle = Rook.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        final boolean canAttack = attackerPiece.canArrive(pathFinder, board);

        assertThat(canAttack).isFalse();
    }
}
