package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BishopTest {

    @Test
    @DisplayName("비숍을 만든다.")
    void createPiece() {
        Piece piece = new Bishop(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 있다.")
    void moveWithDiagonal() {
        Point from = Point.of("c1");
        Point to = Point.of("g5");
        Piece piece = new Bishop(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선이 아닌곳은 이동이 불가능하다.")
    void throwsExceptionWithNotDiagonalDirection() {
        Point from = Point.of("c1");
        Point to = Point.of("a2");
        Piece piece = new Bishop(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }

    @Test
    @DisplayName("장애물이 있을 경우 이동할 수 없다.")
    void notMovableWithObstacle() {
        Point from = Point.of("c1");
        Point to = Point.of("g5");
        Piece piece = new Bishop(Color.WHITE);
        Board board = BoardFixtures.create(Map.of(
                Point.of("d2"), new Pawn(Color.WHITE)
        ));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }

    @Test
    @DisplayName("상하 좌우로 이동할 수 없다.")
    void notMovableWithCross() {
        Point from = Point.of("c1");
        Point to = Point.of("c4");
        Piece piece = new Bishop(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }
}
