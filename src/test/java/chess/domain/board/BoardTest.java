package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("보드 생성기로 보드를 생성한다.")
    void createBoardByGenerator() {
        Board board = Board.of(new InitialBoardGenerator());
        assertThat(board).isNotNull();
    }
}
