package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    @Test
    void 체스판을_초기화_한다() {
        BoardInitializer boardInitializer = new BoardInitializer();
        Board board = boardInitializer.generateBoard();
        assertBlack(board);
        assertWhite(board);
    }

    private void assertBlack(Board board) {
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.A), Color.BLACK)).isInstanceOf(Rook.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.B), Color.BLACK)).isInstanceOf(Knight.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.C), Color.BLACK)).isInstanceOf(Bishop.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.D), Color.BLACK)).isInstanceOf(King.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.E), Color.BLACK)).isInstanceOf(Queen.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.F), Color.BLACK)).isInstanceOf(Bishop.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.G), Color.BLACK)).isInstanceOf(Knight.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.H), Color.BLACK)).isInstanceOf(Rook.class);
        assertThat(board.findPiece(new Position(Row.EIGHT, Column.H), Color.BLACK)).isInstanceOf(Rook.class);

        assertThat(board.findPiece(new Position(Row.SEVEN, Column.A), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.B), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.C), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.D), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.E), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.F), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.G), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.H), Color.BLACK)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.SEVEN, Column.H), Color.BLACK)).isInstanceOf(Pawn.class);
    }

    private void assertWhite(Board board) {
        assertThat(board.findPiece(new Position(Row.ONE, Column.A), Color.WHITE)).isInstanceOf(Rook.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.B), Color.WHITE)).isInstanceOf(Knight.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.C), Color.WHITE)).isInstanceOf(Bishop.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.D), Color.WHITE)).isInstanceOf(King.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.E), Color.WHITE)).isInstanceOf(Queen.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.F), Color.WHITE)).isInstanceOf(Bishop.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.G), Color.WHITE)).isInstanceOf(Knight.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.H), Color.WHITE)).isInstanceOf(Rook.class);
        assertThat(board.findPiece(new Position(Row.ONE, Column.H), Color.WHITE)).isInstanceOf(Rook.class);

        assertThat(board.findPiece(new Position(Row.TWO, Column.A), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.B), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.C), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.D), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.E), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.F), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.G), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.H), Color.WHITE)).isInstanceOf(Pawn.class);
        assertThat(board.findPiece(new Position(Row.TWO, Column.H), Color.WHITE)).isInstanceOf(Pawn.class);
    }
}
