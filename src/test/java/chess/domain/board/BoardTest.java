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

    @Test
    @DisplayName("팀별 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        double initialTotalScore = 38;

        Board board = Board.of(new InitialBoardGenerator());

        assertThat(board.calculateScore().values())
                .containsExactly(initialTotalScore, initialTotalScore);
    }
}
