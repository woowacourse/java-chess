package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("Score 3에 Score 4를 더하면 Score 7을 반환한다.")
    @Test
    void shouldReturnScoreOf7WhenAdd4To3() {
        Score score = new Score(3);
        Score scoreToAdd = new Score(4);
        assertThat(score.add(scoreToAdd)).isEqualTo(new Score(7));
    }
}
