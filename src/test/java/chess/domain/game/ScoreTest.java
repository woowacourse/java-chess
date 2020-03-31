package chess.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("Score 생성")
    void create() {
        Score score = Score.of(1);

        assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("getScore 테스트")
    void getScore() {
        Score score = Score.of(1);

        assertThat(score.getScore()).isEqualTo(1);
    }

    @Test
    @DisplayName("add 테스트")
    void add() {
        Score score = Score.of(1);
        score = score.add(2);

        assertThat(score.getScore()).isEqualTo(3);
    }

    @Test
    @DisplayName("subtract 테스트")
    void subtract() {
        Score score = Score.of(3);
        score = score.subtract(1);

        assertThat(score.getScore()).isEqualTo(2);
    }
}
