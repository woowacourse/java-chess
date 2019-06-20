package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Pawn;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveRuleTest {
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
    void 한칸씩_이동() {
        checkTarget = new Square(Column.D, Row._5);

        piece = new Pawn(Side.WHITE);
        board.setPiece(checkTarget, piece);
        destination = new Square(Column.D, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        piece = new Pawn(Side.BLACK);
        board.setPiece(checkTarget, piece);
        destination = new Square(Column.D, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }

    @Test
    void 처음에_2칸씩_이동() {
        board = Board.makeInitialBoard();

        checkTarget = new Square(Column.A, Row._2);
        destination = new Square(Column.A, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        checkTarget = new Square(Column.A, Row._2);
        destination = new Square(Column.A, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        checkTarget = new Square(Column.A, Row._7);
        destination = new Square(Column.A, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();

        checkTarget = new Square(Column.A, Row._7);
        destination = new Square(Column.A, Row._5);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isTrue();
    }

    @Test
    void 이동불가능한_곳() {
        checkTarget = new Square(Column.D, Row._5);
        piece = new Pawn(Side.BLACK);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.D, Row._6);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.C, Row._5);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        checkTarget = new Square(Column.E, Row._3);
        piece = new Pawn(Side.WHITE);
        board.setPiece(checkTarget, piece);

        destination = new Square(Column.E, Row._2);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.D, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();
    }

    @Test
    void 같은편에_길막당했을때() {
        board = Board.makeInitialBoard();
        board.setPiece(new Square(Column.A, Row._3), new Pawn(Side.WHITE));

        checkTarget = new Square(Column.A, Row._2);
        destination = new Square(Column.A, Row._3);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();

        destination = new Square(Column.A, Row._4);
        result = Rule.isValidMove(board, checkTarget, destination);
        assertThat(result).isFalse();
    }
}