package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판이 생성될 때 모든 기물이 배치된다.")
    void initialChessBoardTest() {
        Board board = new Board();

        assertThat(board.getPiecePoint()).hasSize(32);
    }
}
