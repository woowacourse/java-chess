package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class ScoreTest {

    @Test
    @DisplayName("정적 팩토리 메서드로 Score 객체가 잘 생성되어야 한다.")
    void create() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .isNotNull()
                .isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("10을 파라미터로 넘기면 value로 10.0을 가지고 있어야 한다.")
    void initializeValue() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .extracting("value")
                .isEqualTo(10.0);
    }

    @Test
    @DisplayName("Eqauls 테스트")
    void equals() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .isEqualTo(Score.from(10));
    }

    @Test
    @DisplayName("점수 차 계산 테스트")
    void subtract() {
        Score score = Score.from(100);
        Score other = Score.from(9.9);
        Score result = score.subtract(other);

        Assertions.assertThat(result.getValue()).isEqualTo(90.1);
    }

}
