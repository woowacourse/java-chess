package chess;

import chess.model.Board;
import chess.model.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = Board.create(Pieces.create());
        assertThat(board).isExactlyInstanceOf(Board.class);
    }
}
