package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.unit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardTest {
    private Square square;
    private Board board;
    private Piece piece;

    @BeforeEach
    void setup() {
        board = Board.makeInitialBoard();
    }

    @Test
    void getPiece() {
        square = new Square(Column.A, Row._1);
        piece = board.getPiece(square);
        assertThat(piece).isEqualTo(new Rook(Side.WHITE));

        square = new Square(Column.D, Row._1);
        piece = board.getPiece(square);
        assertThat(piece).isEqualTo(new Queen(Side.WHITE));

        square = new Square(Column.E, Row._7);
        piece = board.getPiece(square);
        assertThat(piece).isEqualTo(new Pawn(Side.BLACK));

        square = new Square(Column.G, Row._8);
        piece = board.getPiece(square);
        assertThat(piece).isEqualTo(new Knight(Side.BLACK));
    }

    @Test
    void getPieceBlank() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            board.getPiece(new Square(Column.C, Row._3));
        });
    }

    @Test
    void setPiece() {
        square = new Square(Column.C, Row._4);
        piece = new Pawn(Side.WHITE);
        board.setPiece(square, piece);
        assertThat(board.getPiece(square)).isEqualTo(piece);
    }

    @Test
    void removePiece() {
        square = new Square(Column.H, Row._8);
        board.removePiece(square);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            board.getPiece(square);
        });
    }
}