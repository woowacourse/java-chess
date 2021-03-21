package chess.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ScoreTest {

    @DisplayName("Score 생성 테스트")
    @Test
    void getScore() {
        Score score = new Score(10.0);

        double scoreValue = score.getScore();

        assertThat(scoreValue).isEqualTo(10.0);
    }

    @DisplayName("Score는 음수값을 가질 수 없다.")
    @Test
    void cannotMakeScore() {
        assertThatCode(() -> new Score(-10.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 0 이상이어야 합니다.");
    }
}
