package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreTest {

    @Test
    @DisplayName("인자로 들어온 점수를 합한 값을 반환한다.")
    void add() {
        // given
        final Score score = Score.create(1);
        final Score expected = Score.create(3.5);

        // when
        final Score actual = score.add(Score.create(2.5));

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("인자로 들어온 점수를 뺀 값을 반환한다.")
    void subtract() {
        // given
        final Score score = Score.create(3.5);
        final Score expected = Score.create(1);

        // when
        final Score actual = score.subtract(Score.create(2.5));

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("인자로 들어온 횟수만큼 곱한 값을 반환한다.")
    void multiply() {
        // given
        final Score score = Score.create(0.5);
        final Score expected = Score.create(1);

        // when
        final Score actual = score.multiply(2);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
