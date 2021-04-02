package chess.domain.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @Test
    @DisplayName("0에서 점수 더하기")
    void add() {
        assertThat(Score.ZERO.add(new Score(3))).isEqualTo(new Score(3));
    }

    @Test
    @DisplayName("1에서 점수 더하기")
    void add1() {
        assertThat(new Score(1).add(new Score(3))).isEqualTo(new Score(4));
    }

    @Test
    @DisplayName("1에서 점수 빼기")
    void add2() {
        assertThat(new Score(1).add(new Score(-3))).isEqualTo(new Score(-2));
    }

    @Test
    @DisplayName("base를 기준으로 비교")
    void compareTo() {
        final Score score = new Score(3);

        assertTrue(score.compareTo(new Score(2)) > 0);
        assertTrue(score.compareTo(new Score(3)) == 0);
        assertTrue(score.compareTo(new Score(4)) < 0);
    }

}