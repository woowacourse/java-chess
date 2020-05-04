package chess.domain.piece.score;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void add() {
        Score score = Score.of(1);
        score = score.add(Score.of(3));
        assertThat(score).isEqualTo(Score.of(4));
    }
}