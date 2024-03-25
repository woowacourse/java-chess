package chess.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @Test
    @DisplayName("동등성을 올바르게 판단한다.")
    void identityTest() {
        // given
        Score score = Score.of(1);
        // when, then
        assertThat(score).isEqualTo(Score.of(1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.9999999999, 1.1})
    @DisplayName("0.5배수가 아닌 점수를 생성하는 경우 예외를 발생한다.")
    void illgalScoreTest(double score) {
        assertThatThrownBy(() -> Score.of(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 0.5의 배수여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.5})
    @DisplayName("0.5배수인 정수를 올바르게 생성한다.")
    void scoreCreationTest(double score) {
        assertDoesNotThrow(() -> Score.of(score));
    }

    @Test
    @DisplayName("음수 점수를 생성하는 경우 예외를 발생한다.")
    void negativeScoreCreationTest() {
        assertThatThrownBy(() -> Score.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 음수가 될 수 없습니다.");
    }

    @Test
    @DisplayName("점수 덧셈이 올바르게 동작한다.")
    void scoreAdditionTest() {
        // given
        Score score = Score.of(1);
        // when
        Score actual = score.add(Score.of(2));
        // then
        assertThat(actual).isEqualTo(Score.of(3));
    }


    @Test
    @DisplayName("점수 곱셈이 올바르게 동작한다.")
    void scoreMultiplicationTest() {
        // given
        Score score = Score.of(3);
        // when
        Score actual = score.multiplyBy(7);
        // then
        assertThat(actual).isEqualTo(Score.of(21));
    }


    @Test
    @DisplayName("점수를 절반으로 올바르게 나눈다.")
    void divideScoreTest() {
        // given
        Score score = Score.of(10);
        // when
        Score actual = score.divideInHalf();
        // then
        assertThat(actual).isEqualTo(Score.of(5));
    }

    @Test
    @DisplayName("소수점 부분이 존재하는 점수를 절반으로 나누는 경우 예외를 발생한다.")
    void dividingScoreWithDecimalPartTest() {
        // given
        Score score = Score.of(1.5);
        // when, then
        assertThatThrownBy(score::divideInHalf)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("나눌 점수는 소수점 부분이 존재하지 않아야 합니다.");
    }
}
