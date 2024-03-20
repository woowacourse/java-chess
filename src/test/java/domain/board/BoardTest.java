package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        Board board = Board.initialize();
        assertThat(board.getSquares()).hasSize(32);
    }
}
