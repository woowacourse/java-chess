package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("Board를 초기화한다.")
    void create() {
        assertThat(Board.create()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("Position을 입력하면 piece를 반환한다.")
    void getPiece() {
        Board board = Board.create();
        assertThat(board.getPiece(new Position(Column.A, Row.EIGHT))).isEqualTo(new Rook(Color.BLACK));
    }
}
