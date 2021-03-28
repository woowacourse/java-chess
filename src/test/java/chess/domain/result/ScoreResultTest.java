package chess.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreResultTest {

    @DisplayName("점수 계산 테스트")
    @Test
    void yield() {
        // given, when
        char[][] viewBoard = {
            {'.', 'K', 'R', '.', '.', '.', '.', '.'},
            {'P', '.', 'P', 'B', '.', '.', '.', '.'},
            {'.', 'P', '.', '.', 'Q', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', 'n', 'q', '.'},
            {'.', '.', '.', '.', '.', 'p', '.', 'p'},
            {'.', '.', '.', '.', '.', 'p', 'p', '.'},
            {'.', '.', '.', '.', 'r', 'k', '.', '.'}
        };
        Board board = BoardUtil.generateBoard(viewBoard);
        ScoreResult scoreResult = ScoreResult.yield(board.toList());

        // then
        assertThat(scoreResult.getWhiteScore()).isEqualTo(19.5);
        assertThat(scoreResult.getBlackScore()).isEqualTo(20.0);
    }
}