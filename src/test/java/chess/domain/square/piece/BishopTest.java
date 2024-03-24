package chess.domain.square.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.Path;
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
    void canMoveTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("비숍은 대각선 경로가 아니면 움직일 수 없다.")
    @Test
    void canNotMoveInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        board.put(new Position(Rank.FIRST, File.A), piece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(piece.canArrive(path, board))
                .isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 움직일 수 없다.")
    @Test
    void canNotMoveWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece piece = Bishop.from(Color.WHITE);
        Piece obstacle = Bishop.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), piece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(piece.canArrive(path, board))
                .isFalse();
    }

    @DisplayName("비숍은 대각선 경로이고, 경로에 장애물이 없는 경우 공격할 수 있다.")
    @Test
    void canAttackTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canArrive(path, board))
                .isTrue();
    }

    @DisplayName("비숍은 대각선 경로가 아니면 공격할 수 없다.")
    @Test
    void canNotAttackInvalidPathTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.FIRST, File.C), attackedPiece);
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.C));

        // when
        assertThat(attackerPiece.canArrive(path, board))
                .isFalse();
    }

    @DisplayName("경로에 장애물이 있으면 공격할 수 없다.")
    @Test
    void canNotAttackWithObstacleTest() {
        // given
        final Map<Position, Square> board = EmptySquaresMaker.make();
        Piece attackerPiece = Bishop.from(Color.WHITE);
        Piece attackedPiece = Bishop.from(Color.BLACK);
        Piece obstacle = Bishop.from(Color.BLACK);

        board.put(new Position(Rank.FIRST, File.A), attackerPiece);
        board.put(new Position(Rank.SECOND, File.B), obstacle);
        board.put(new Position(Rank.THIRD, File.C), attackedPiece);

        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.THIRD, File.C));

        // when
        assertThat(attackerPiece.canArrive(path, board))
                .isFalse();
    }
}
