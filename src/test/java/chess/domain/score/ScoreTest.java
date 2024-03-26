package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("두 Score를 더하여 새로운 Score를 반환한다.")
    void addScores() {
        Score base = new Score(1);
        Score increment = new Score(1.5);

        assertThat(base.add(increment)).isEqualTo(new Score(2.5));
    }

    @Test
    @DisplayName("Score에서 특정 값을 뺀 새로운 Score를 반환한다.")
    void subtractScore() {
        Score base = new Score(2);
        double subtrahend = 0.5;

        assertThat(base.subtract(subtrahend)).isEqualTo(new Score(1.5));
    }
}
