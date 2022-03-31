package chess.domain.game.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @DisplayName("from에 음수가 아닌 실수를 전달하여 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(doubles = {0, 1.1, 10,})
    void from_returnsNewScore(double input) {
        // given & when
        Score actual = Score.from(input);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("from에 음수를 전달하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(doubles = {-10, -1, -0.1})
    void from_throwsExceptionWithNegative(double input) {
        // given & when & then
        assertThatThrownBy(() -> Score.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수를 전달할 수 없습니다.");
    }

    @DisplayName("같은 값을 가지고 있는 두 Score은 동등성을 갖는다.")
    @ParameterizedTest
    @ValueSource(doubles = {0, 10.5, 100})
    void hasEquality(double input) {
        // given
        Score actual = Score.from(input);
        Score same = Score.from(input);

        // when
        boolean expected = actual.equals(same);

        // then
        assertThat(expected).isTrue();
    }

    @DisplayName("add 메소드는 현재 Score값과 다른 Score값을 더한 새 Score를 생성하여 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2,3,5", "2.5,3.5,6", "0,0,0"})
    void add_returnsAddedNewScore(double first, double second, double result) {
        // given
        Score firstScore = Score.from(first);
        Score secondScore = Score.from(second);

        // when
        Score actual = firstScore.add(secondScore);

        // then
        assertThat(actual).isEqualTo(Score.from(result));
    }

    @DisplayName("subtract 메소드는 현재 Score값과 다른 Score값을 뺀 새 Score를 생성하여 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"3,2,1", "3.5,2.0,1.5", "0,0,0"})
    void subtract_returnsSubtractedNewScore(double first, double second, double result) {
        // given
        Score firstScore = Score.from(first);
        Score secondScore = Score.from(second);

        // when
        Score actual = firstScore.subtract(secondScore);

        // then
        assertThat(actual).isEqualTo(Score.from(result));
    }
}
