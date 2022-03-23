package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("")
    void createBoardByGenerator() {
        Board board = Board.of(new BoardGenerator());
        assertThat(board).isNotNull();
    }
}
