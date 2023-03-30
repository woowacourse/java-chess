package chess.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("Socre를 더하고, 반환해주는 기능 추가")
    void test_sum() {
        final Score score = new Score(5);
        final Score addition = new Score(4);

        final Score sum = score.sum(addition);

        assertThat(sum.getValue()).isEqualTo(9);
    }

    @Test
    @DisplayName("Score에 숫자를 곱하는 기능 추가")
    void test_multiply() {
        final Score score = new Score(10);
        final Score multiply = score.multiply(0.5);

        assertThat(multiply.getValue()).isEqualTo(5);
    }

    @Test
    @DisplayName("Score에 숫자를 빼는 기능 추가")
    void test_subtract() {
        final Score score = new Score(10);
        final Score subtrahend = new Score(20);

        assertThat(score.subtract(subtrahend)).isEqualTo(new Score(-10));
    }
}
