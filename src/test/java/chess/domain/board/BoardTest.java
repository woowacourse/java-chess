package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.pieces.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class BoardTest {

    @Test
    @DisplayName("생성된 board의 세로 길이는 8개 이다.")
    void create_test_height() {
        // given
        Board board = Board.create();

        // then
        assertThat(board.getBoard()).hasSize(8);
    }

    @Test
    @DisplayName("생성된 board의 가로 길이는 8개 이다.")
    void create_test_width() {
        // given
        Board board = Board.create();
        List<Rank> ranks = board.getBoard();

        // then
        assertThat(ranks.get(0).getRank()).hasSize(8);
    }
}
