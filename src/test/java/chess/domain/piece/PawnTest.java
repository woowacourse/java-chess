package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class PawnTest {

    @Test
    @DisplayName("폰을 만든다.")
    void createPiece() {
        Piece piece = new Pawn(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("(흰색 폰) 한 칸 움직인다.")
    void moveForwardOneStepWhite() {
        Piece piece = new Pawn(Color.WHITE);

        Point from = Point.of("a2");
        Point to = Point.of("a3");
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(검정 폰) 한 칸 움직인다.")
    void moveForwardOneStepBlack() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("b7");
        Point to = Point.of("b6");
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(흰색 폰) 초기 위치에서 두 칸 움직인다.")
    void moveForwardTwoStepWhite() {
        Piece piece = new Pawn(Color.WHITE);

        Point from = Point.of("a2");
        Point to = Point.of("a4");
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(검정 폰) 초기 위치에서 두 칸 움직인다.")
    void moveForwardTwoStepBlack() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("b7");
        Point to = Point.of("b5");
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("폰이 초기 위치에서 두 칸을 움직일 떄, 도착 위치에 말이 있으면 예외가 발생한다.")
    void moveForwardTwoStepExceptionTest() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("b7");
        Point to = Point.of("b5");
        Board board = BoardFixtures.create(
                Map.of(Point.of(2, 5), new Rook(Color.BLACK)));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }

    @Test
    @DisplayName("폰이 초기 위치에서 두 칸을 움직일 떄, 중간 위치에 말이 있으면 예외가 발생한다.")
    void moveForwardTwoStepExceptionTest2() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("b7");
        Point to = Point.of("b5");
        Board board = BoardFixtures.create(
                Map.of(Point.of(2, 5), new Rook(Color.BLACK)));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }

    @Test
    @DisplayName("(흰색 폰)적이 있다면 대각선으로 한 칸 이동 가능하다.")
    void moveDiagonalWhite() {
        Piece piece = new Pawn(Color.WHITE);

        Point from = Point.of("a4");
        Point to = Point.of("b5");
        Board board = BoardFixtures.create(
                Map.of(Point.of(2, 5), new Rook(Color.BLACK)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(검정 폰)적이 있다면 대각선으로 한 칸 이동 가능하다.")
    void moveDiagonalBlack() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("a5");
        Point to = Point.of("b4");
        Board board = BoardFixtures.create(
                Map.of(Point.of("b4"), new Rook(Color.WHITE)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(흰색 폰)시작위치에서는 적이 있어도 대각선으로 이동할 수 있다.")
    void moveDiagonalFromStartLineWhite() {
        Piece piece = new Pawn(Color.WHITE);

        Point from = Point.of("a2");
        Point to = Point.of("b3");
        Board board = BoardFixtures.create(
                Map.of(to, new Rook(Color.BLACK)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("(검정 폰)시작위치에서는 적이 있어도 대각선으로 이동할 수 있다.")
    void moveDiagonalFromStartLineBlack() {
        Piece piece = new Pawn(Color.BLACK);

        Point from = Point.of("a7");
        Point to = Point.of("b6");
        Board board = BoardFixtures.create(
                Map.of(to, new Rook(Color.WHITE)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }
}
