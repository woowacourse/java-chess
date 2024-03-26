package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
