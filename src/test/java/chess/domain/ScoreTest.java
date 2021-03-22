package chess.domain;

import chess.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @DisplayName("점수 객체를 생성한다.")
    @Test
    void 점수_객체_생성() {
        Score score = new Score(5);

        double value = score.getValue();

        assertThat(value).isEqualTo(5);
    }

    @DisplayName("점수를 더한다.")
    @Test
    void 점수를_더한다() {
        Score score1 = new Score(5);
        Score score2 = new Score(2.5);

        double value = score1.sum(score2);

        assertThat(value).isEqualTo(7.5);
    }

    @DisplayName("count만큼 0.5점을 뺀 값을 구한다.")
    @Test
    void 빼야하는_값을_뺀_점수를_구한다() {
        Score score = new Score(5);
        int sameXPawnCount = 3;

        double result = score.subtractedByMultipliedCount(sameXPawnCount);

        assertThat(result).isEqualTo(3.5);
    }
}
