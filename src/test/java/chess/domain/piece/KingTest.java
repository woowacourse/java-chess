package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;

public class KingTest {

    @Test
    @DisplayName("킹을 만든다.")
    void createPiece() {
        Piece piece = new King(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("앞으로 한 칸 움직인다.")
    void moveForwardOneStep() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("e2");
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("대각선으로 한 칸 움직인다.")
    void moveDiagonalOneStep() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d2");
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("옆으로 한 칸 움직인다.")
    void moveSideOneStep() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d1");
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("킹은 두 칸 이상 움직이지 못한다.")
    void notMovable() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d4");
        Board board = BoardFixtures.empty();

        boolean isMovable = piece.move(board, from, to);

        assertThat(isMovable).isFalse();
    }
}
