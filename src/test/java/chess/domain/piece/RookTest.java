package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class RookTest {

    @Test
    @DisplayName("룩을 만든다.")
    void createPiece() {
        Piece piece = new Rook(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("상하좌우의 직선으로 이동할 수 있다.")
    void movableTest() {
        Point from = Point.of("a1");
        Point to = Point.of("a7");
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("장애물이 있을 경우 이동할 수 없다.")
    void notMovableWithObstacle() {
        Point from = Point.of("a1");
        Point to = Point.of("a7");
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.create(Map.of(
                Point.of("a5"), new Pawn(Color.WHITE)
        ));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }

    @Test
    @DisplayName("대각선으로 이동할 수 없다.")
    void notMovableWithDiagonal() {
        Point from = Point.of("a1");
        Point to = Point.of("c3");
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }
}
