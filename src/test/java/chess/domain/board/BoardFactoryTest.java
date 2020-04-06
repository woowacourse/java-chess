package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardFactoryTest {
    @DisplayName("체스 보드 생성")
    @Test
    void createBoard() {
        Board board = BoardFactory.createInitializedBoard();
        assertThat(board).isNotNull();
    }
}
