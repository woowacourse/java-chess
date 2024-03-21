package chess.domain;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드 생성 시 32개의 기물이 초기화된다.")
    void printMap() {
        Board board = new Board();

        Map<Position, Piece> boardMap = board.getBoard();

        Assertions.assertThat(boardMap).hasSize(32);
    }
}
