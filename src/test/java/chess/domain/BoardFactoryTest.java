package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @Test
    @DisplayName("보드 생성")
    void construct() {
        Board board = BoardFactory.create();
        assertThat(board).isNotNull();
        assertThat(board.size()).isEqualTo(64);
    }
}
