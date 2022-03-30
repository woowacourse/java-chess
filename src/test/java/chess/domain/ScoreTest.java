package chess.domain;

import chess.domain.state.BoardInitialize;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScoreTest {
    @Test
    @DisplayName("Board와 Team을 입력 받아 현재 말의 총 점수를 조회한다.")
    void createTotalScore() {
        WhiteTurn whiteTurn = new WhiteTurn(BoardInitialize.create());
        int totalScore = (int) new Score(whiteTurn.getBoard(), Team.WHITE).getTotalScore();
        assertThat(totalScore).isEqualTo(38);
    }
}