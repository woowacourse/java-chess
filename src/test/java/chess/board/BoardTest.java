package chess.board;

import chess.board.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = Board.create(Pieces.createInit(), Turn.init());
        assertThat(board).isExactlyInstanceOf(Board.class);
    }
}
