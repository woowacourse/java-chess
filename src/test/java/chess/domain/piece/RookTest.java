package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;

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
        Point from = Point.of(1, 1);
        Point to = Point.of(1, 7);
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("장애물이 있을 경우 이동할 수 없다.")
    void notMovableWithObstacle() {
        Point from = Point.of(1, 1);
        Point to = Point.of(1, 7);
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.create(Map.of(
            Point.of(1, 5), new Pawn(Color.WHITE)
        ));

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isFalse();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 없다.")
    void notMovableWithDiagonal() {
        Point from = Point.of(1, 1);
        Point to = Point.of(3, 3);
        Piece piece = new Rook(Color.WHITE);
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isFalse();
    }
}
