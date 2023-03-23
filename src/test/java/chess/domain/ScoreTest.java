package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    
    @Test
    @DisplayName("점수를 더한다.")
    void addScore() {
        Score score = new Score(3);

        assertThat(score.add(new Score(2.5))).isEqualTo(new Score(5.5));
    }
}
