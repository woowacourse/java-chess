package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("White 팀 말들 초기 점수 조회 테스트")
    void getWhiteTeamScore() {
        assertThat(Score.calculateScore(Board.initBoard().getBoard(), Team.WHITE).getTotalScore()).isEqualTo(38);
    }

    @Test
    @DisplayName("Black 팀 말들 초기 점수 조회 테스트")
    void getBlackTeamScore() {
        assertThat(Score.calculateScore(Board.initBoard().getBoard(), Team.BLACK).getTotalScore()).isEqualTo(38);
    }
}