package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("Board 를 초기화한다.")
    void create() {
        assertThat(BoardFactory.createChessBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("Position 을 입력하면 piece 를 반환한다.")
    void getPiece() {
        Board board = BoardFactory.createChessBoard();
        assertThat(board.getPiece(Position.valueOf("a8"))).isEqualTo(new Rook(Color.BLACK));
    }
}
