package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("보드 초기화")
    @Test
    void init() {
        Board board = BoardInitializer.init();
        assertThat(board).isEqualTo(BoardInitializer.init());
    }
}