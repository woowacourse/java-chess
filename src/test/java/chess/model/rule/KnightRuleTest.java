package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Knight;
import chess.model.unit.Pawn;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightRuleTest {
    private Board board;
    private Square checkTarget;
    private Square destination;
    private Piece piece;
    private boolean result;

    @BeforeEach
    void setUp() {
        board = Board.makeEmptyBoard();
    }

    @Test
    void 정해진_위치로_이동() {
        checkTarget = new Square(Column.D, Row._5);
        piece = new Knight(Side.WHITE);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.B, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.C, Row._7);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.E, Row._7);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.F, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.B, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.C, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.E, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.F, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }

    @Test
    void 좌측_상단_끄트머리() {
        checkTarget = new Square(Column.A, Row._8);
        piece = new Knight(Side.BLACK);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.B, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.C, Row._7);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }

    @Test
    void 우측_하단_끄트머리() {
        checkTarget = new Square(Column.H, Row._1);
        piece = new Knight(Side.WHITE);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.F, Row._2);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        destination = new Square(Column.G, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }

    @Test
    void 이동불가능한_곳() {
        checkTarget = new Square(Column.D, Row._5);
        piece = new Knight(Side.BLACK);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.C, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.B, Row._7);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.D, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();
    }

    @Test
    void 이동불가능한_곳_좌상단끄트머리() {
        checkTarget = new Square(Column.A, Row._8);
        piece = new Knight(Side.WHITE);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.A, Row._7);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.C, Row._8);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.C, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();
    }

    @Test
    void 이동불가능한_곳_우하단끄트머리() {
        checkTarget = new Square(Column.H, Row._1);
        piece = new Knight(Side.WHITE);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.F, Row._1);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.H, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.G, Row._2);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.G, Row._1);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();
    }

    @Test
    void 같은편에_길막당했을때() {
        checkTarget = new Square(Column.D, Row._5);
        piece = new Knight(Side.BLACK);
        board.setPiece(checkTarget, piece);
        piece = new Pawn(Side.BLACK);
        board.setPiece(new Square(Column.E, Row._3), piece);

        destination = new Square(Column.E, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.F, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }
}