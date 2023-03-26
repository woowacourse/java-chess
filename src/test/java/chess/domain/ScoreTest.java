package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("점수 계산 초기 값은 0이다.")
    void score_init_is_zero() {
        assertThat(Score.init().getValue()).isZero();
    }

    @Test
    @DisplayName("초기 점수에 2.5점을 더하면 2.5점이 된다.")
    void plus_test1() {
        Score score = Score.init();
        Score addedScore = score.plus(new Score(2.5));

        assertThat(addedScore.getValue()).isEqualTo(2.5);
    }

    @Test
    @DisplayName("초기 점수에 2.5점과 5점을 더하면 7.5점이 된다.")
    void plus_test2() {
        Score score = Score.init();
        Score addedScore = score.plus(new Score(2.5));
        Score secondAddedScore = addedScore.plus(new Score(5));

        assertThat(secondAddedScore.getValue()).isEqualTo(7.5);
    }

    @Test
    @DisplayName("3점에서 0.5점을 빼면 2.5점이다.")
    void minus_test() {
        Score score = new Score(3);
        Score minusScore = score.minus(new Score(0.5));

        assertThat(minusScore.getValue()).isEqualTo(2.5);
    }
}