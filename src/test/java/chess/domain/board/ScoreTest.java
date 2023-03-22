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
}
