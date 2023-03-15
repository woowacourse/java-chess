package chess.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("tewst")
    void containsPieces() {
        Board board = new Board();
        assertThat(board.getPieces()).hasSize(32);
    }
}
