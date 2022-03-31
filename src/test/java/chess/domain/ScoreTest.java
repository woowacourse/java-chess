package chess.domain;

import chess.domain.state.BoardInitialize;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    @Test
    @DisplayName("현재 체스 보드를 입력 받아, 각 팀의 점수를 조회한다.")
    void getTotalScore() {
        GameState chessGame = new WhiteTurn(BoardInitialize.create());
        Score score = new Score(chessGame.getBoard());
        assertThat(score.getTotalScoreWhiteTeam()).isEqualTo(38.0f);
        assertThat(score.getTotalScoreBlackTeam()).isEqualTo(38.0f);
    }
}