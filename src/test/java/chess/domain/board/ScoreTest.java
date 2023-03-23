package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("스코어를 받아서 더해진 스코어를 반환한다.")
    void add() {
        // given
        Score originalScore = new Score(new BigDecimal("1.0"));
        Score scoreToAdd = new Score(new BigDecimal("2.0"));

        // when
        Score addedScore = originalScore.add(scoreToAdd);

        // then
        assertThat(addedScore).isEqualTo(new Score(new BigDecimal("3.0")));
    }

    @Test
    @DisplayName("스코어를 받아서 뺀 스코어를 반환한다.")
    void subtract() {
        // given
        Score originalScore = new Score(new BigDecimal("10.0"));
        Score scoreToSubtract = new Score(new BigDecimal("2.0"));

        // when
        Score subtractScore = originalScore.subtract(scoreToSubtract);

        // then
        assertThat(subtractScore).isEqualTo(new Score(new BigDecimal("8.0")));
    }

    @Test
    @DisplayName("스코어를 비교하여 비교할 스코어보다 크면 true를 반환한다.")
    void isGreaterScore() {
        // given
        Score score = new Score(new BigDecimal("10.0"));
        Score scoreToCompare = new Score(new BigDecimal("8.0"));

        // when, then
        assertThat(score.isGreaterScore(scoreToCompare)).isTrue();
    }

    @Test
    @DisplayName("스코어를 비교하여 비교할 스코어보다 작으면 true를 반환한다.")
    void isLessScore() {
        // given
        Score score = new Score(new BigDecimal("8.0"));
        Score scoreToCompare = new Score(new BigDecimal("10.0"));

        // when, then
        assertThat(score.isLessScore(scoreToCompare)).isTrue();
    }

    @Test
    @DisplayName("스코어를 비교하여 비교할 스코어와 같으면 true를 반환한다.")
    void isEqualScore() {
        // given
        Score score = new Score(new BigDecimal("8.0"));
        Score scoreToCompare = new Score(new BigDecimal("8.0"));

        // when, then
        assertThat(score.isEqualScore(scoreToCompare)).isTrue();
    }
}
