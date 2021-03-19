package chess.domain.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 점수_객체_생성() {
        Score score = new Score(2.5);

        assertThat(score.getValue()).isEqualTo(2.5);
    }
}
