package chess.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("점수")
class ScoreTest {

    @DisplayName("적절한 점수를 생성한다")
    @Test
    void createScore() {
        //given
        double validScore = 10.0;
        double inValidScore = -1.2;

        //when
        Score score = Score.of(10.0);

        //then
        assertThat(score.getValue()).isEqualTo(validScore);
        assertThatThrownBy(() -> Score.of(inValidScore))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("절반으로 감소한다")
    @Test
    void halfScore() {
        //given
        double validScore = 10.0;
        Score score = Score.of(10.0);

        //when
        Score halfScore = score.half();

        //then
        assertThat(halfScore.getValue()).isEqualTo(validScore / 2);
    }
}