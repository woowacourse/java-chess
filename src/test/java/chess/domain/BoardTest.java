package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    @DisplayName("보드 생성")
    void construct() {
        Board board = Board.of();
        assertThat(board).isNotNull();
        assertThat(board.size()).isEqualTo(64);
    }
}
