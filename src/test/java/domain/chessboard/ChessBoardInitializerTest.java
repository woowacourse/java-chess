package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardInitializerTest {

    @DisplayName("보드를 초기화한다.")
    @Test
    void initializeBoard() {
        assertThat(ChessBoardInitializer.createInitialBoard()).hasSize(64);
    }
}
