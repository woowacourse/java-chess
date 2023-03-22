package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("Score와 pawnCount를 전달 받아 최종 스코어를 반환한다.")
    void calculateScoreWithPawnCount() {
        Score score = new Score(25.5);

        Score result = score.calculateScoreWithPawnCount(3);

        assertThat(result.getScore()).isEqualTo(24);
    }
}
