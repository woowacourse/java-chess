package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        Board board = BoardFixtures.EMPTY_BOARD;

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선으로 한 칸 움직인다.")
    void moveDiagonalOneStep() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d2");
        Board board = BoardFixtures.EMPTY_BOARD;

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("옆으로 한 칸 움직인다.")
    void moveSideOneStep() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d1");
        Board board = BoardFixtures.EMPTY_BOARD;

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹은 두 칸 이상 움직이지 못한다.")
    void notMovable() {
        Piece piece = new King(Color.BLACK);

        Point from = Point.of("e1");
        Point to = Point.of("d4");
        Board board = BoardFixtures.EMPTY_BOARD;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }
}
