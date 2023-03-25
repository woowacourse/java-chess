package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("점수를 더해 새로운 Score 객체를 반환한다")
    void 점수를_더해_새로운_Score_객체를_반환한다() {
        Score score = new Score(10);
        Score otherScore = new Score(5);

        assertThat(score.add(otherScore)).isEqualTo(new Score(15));
    }

    @Test
    @DisplayName("논리적으로 같은 객체가 된다")
    void 논리적으로_같은_객체가_된다() {
        Score score = new Score(30.0);
        Score otherScore = new Score(30.0);

        assertThat(score).isEqualTo(otherScore);
    }
}
