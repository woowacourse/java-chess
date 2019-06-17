package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.unit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BoardTest {
    private Board board;
    private Piece piece;

    @BeforeEach
    void setup() {
        board = Board.makeInitialBoard();
    }

    @Test
    void getPiece() {
        piece = board.getPiece(Column.A, Row._1);
        assertThat(piece).isEqualTo(new Rook(Side.WHITE));

        piece = board.getPiece(Column.D, Row._1);
        assertThat(piece).isEqualTo(new Queen(Side.WHITE));

        piece = board.getPiece(Column.E, Row._7);
        assertThat(piece).isEqualTo(new Pawn(Side.BLACK));

        piece = board.getPiece(Column.G, Row._8);
        assertThat(piece).isEqualTo(new Knight(Side.BLACK));
    }

    @Test
    void getPieceBlank() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            board.getPiece(Column.C, Row._3);
        });
    }

    @Test
    void setPiece() {
        Column column = Column.C;
        Row row = Row._4;
        piece = new Pawn(Side.WHITE);
        board.setPiece(column, row, piece);
        assertThat(board.getPiece(column, row)).isEqualTo(piece);
    }

    @Test
    void removePiece() {
        Column column = Column.H;
        Row row = Row._8;
        board.removePiece(column, row);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            board.getPiece(column, row);
        });
    }
}