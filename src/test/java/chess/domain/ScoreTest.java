package chess.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    void create() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .isNotNull()
                .isInstanceOf(Score.class);
    }

    @Test
    void initializeValue() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .extracting("value")
                .isEqualTo(10);
    }

    @Test
    void equals() {
        final var score = Score.from(10);
        Assertions.assertThat(score)
                .isEqualTo(Score.from(10));
    }

}
