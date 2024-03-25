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
    void canStraightMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 이동할 수 있다.")
    @Test
    void canDiagonalMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveStraightWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveDiagonalWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Queen.from(Color.WHITE);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("퀸은 직선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canStraightAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.EIGHTH, File.A), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.EIGHTH, File.A));

        // when & then
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canDiagonalAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isTrue();
    }

    @DisplayName("퀸은 대각선 또는 직선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.C), attackedPiece);
        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.C));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("직선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackStraightWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        board.put(new Position(Rank.FIRST, File.B), obstacle);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }

    @DisplayName("대각선 경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackDiagonalWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Queen.from(Color.WHITE);
        Piece attackedPiece = Queen.from(Color.BLACK);
        Piece obstacle = Queen.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        PathFinder pathFinder = new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canArrive(pathFinder, board))
                .isFalse();
    }
}
