package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.piece.Knight;
import chess.piece.Piece;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void 체스판의_특정_위치에_아군_말이_존재하는지_확인한다() {
        Board board = new Board();
        Position position = new Position(Row.SEVEN, Column.C);
        board.put(new Knight(position, board, Color.BLACK));

        assertThat(board.existSameTeam(position, Color.BLACK)).isTrue();
    }

    @Test
    void 체스판에서_특정_위치에_있는_특정_팀의_기물을_찾는다() {
        Board board = new Board();
        Position position = new Position(Row.SEVEN, Column.C);
        board.put(new Knight(position, board, Color.BLACK));

        Piece piece = board.findPiece(position, Color.BLACK);

        assertThat(piece.getPosition()).isEqualTo(position);
        assertThat(piece.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    void 자신의_기물이_아닌_것을_움직이면_예외가_발생한다() {
        Board board = new Board();
        Position position = new Position(Row.SEVEN, Column.C);
        board.put(new Knight(position, board, Color.BLACK));

        assertThatThrownBy(() -> board.findPiece(position, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 특정_위치에_있는_기물을_제거한다() {
        Board board = new Board();
        Position position = new Position(Row.SEVEN, Column.C);
        board.put(new Knight(position, board, Color.WHITE));

        board.removeByPosition(position);

        assertThat(board.existPiece(position)).isFalse();
    }
}
