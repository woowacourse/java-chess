package chess.domain;

import static chess.domain.Pieces.createWhitePieces;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public final class ScoreTest {

    @Test
    void create() {
        final var score = Score.from(10);
        assertThat(score)
                .isNotNull()
                .isInstanceOf(Score.class);
    }

    @Test
    void initializeValue() {
        final var score = Score.from(10);
        assertThat(score)
                .extracting("value")
                .isEqualTo(10.0);
    }

    @Test
    void equals() {
        final var score = Score.from(10);
        assertThat(score)
                .isEqualTo(Score.from(10));
    }

    @Test
    void subtract() {
        assertThat(Score.subtract(createWhitePieces())).isEqualTo(Score.from(38.0));
    }

}
