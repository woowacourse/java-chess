package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class QueenTest {

    @Test
    @DisplayName("퀸을 만든다.")
    void createPiece() {
        Piece piece = new Queen(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("상하좌우의 직선으로 이동할 수 있다.")
    void moveWithCross() {
        Point from = Point.of(1, 1);
        Point to = Point.of(1, 7);
        Piece piece = new Queen(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("대각선으로 이동할 수 있다.")
    void moveWithDiagonal() {
        Point from = Point.of("c1");
        Point to = Point.of("g5");
        Piece piece = new Queen(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board, from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸은 직선으로만 이동할 수 있다.")
    void notMovableWithoutStraight() {
        Point from = Point.of(1, 1);
        Point to = Point.of(2, 7);
        Piece piece = new Queen(Color.WHITE);
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }

    @Test
    @DisplayName("장애물이 있을 경우 상하좌우로 이동할 수 없다.")
    void notMovableWithCrossObstacle() {
        Point from = Point.of(1, 1);
        Point to = Point.of(1, 7);
        Piece piece = new Queen(Color.WHITE);
        Board board = BoardFixtures.create(Map.of(
                Point.of(1, 5), new Pawn(Color.WHITE)
        ));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }

    @Test
    @DisplayName("장애물이 있을 경우 대각선으로 이동할 수 없다.")
    void notMovableWithDiagonalObstacle() {
        Point from = Point.of("c1");
        Point to = Point.of("g5");
        Piece piece = new Queen(Color.WHITE);
        Board board = BoardFixtures.create(Map.of(
                Point.of("d2"), new Pawn(Color.WHITE)
        ));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board, from, to));
    }
}
