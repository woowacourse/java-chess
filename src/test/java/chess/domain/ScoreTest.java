package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @DisplayName("초기 백팀 점수 테스트")
    @Test
    void whiteScore() {
        Score score = Score.from(BoardFixture.setup());

        assertThat(score.whiteScore()).isEqualTo(38.0);
    }

    @DisplayName("초기 흑팀 점수 테스트")
    @Test
    void blackScore() {
        Score score = Score.from(BoardFixture.setup());

        assertThat(score.blackScore()).isEqualTo(38.0);
    }
}
