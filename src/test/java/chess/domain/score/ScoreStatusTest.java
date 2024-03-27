package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("점수 상태")
class ScoreStatusTest {
    @DisplayName("기본 점수 계산한다")
    @Test
    void defaultScore() {
        //given
        double validScore = 10.0;
        Score score = Score.of(10.0);

        //when
        Score calculatedScore = ScoreStatus.DEFAULT.calculate(score);

        //then
        assertThat(calculatedScore.getValue()).isEqualTo(validScore);
    }

    @DisplayName("반절 점수 계산한다")
    @Test
    void halfScore() {
        //given
        double validScore = 10.0;
        Score score = Score.of(10.0);

        //when
        Score calculatedScore = ScoreStatus.HALF.calculate(score);

        //then
        assertThat(calculatedScore.getValue()).isEqualTo(validScore / 2);
    }
}
