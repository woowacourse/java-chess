package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @DisplayName("8 * 8 의 빈 체스 판 싱글톤 테스트")
    @Test
    void singleton() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();

        assertThat(board1).isSameAs(board2);
    }
}