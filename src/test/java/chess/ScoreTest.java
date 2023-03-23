package chess;

import chess.domain.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    @DisplayName("두 점수를 더한다.")
    void sum() {
        Score score1 = new Score(10);
        Score score2 = new Score(0.5);

        Score result = score1.sum(score2);

        Assertions.assertThat(result).isEqualTo(new Score(10.5));
    }
}
