package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임판")
class BoardTest {
    @DisplayName("초기화에 성공한다.")
    @Test
    void initialize() {
        //given
        Board board = BoardFactory.createBoard();
        int expectedSize = 64;

        //when & then
        assertThat(board.getPieces().keySet().size()).isEqualTo(expectedSize);
    }
}
