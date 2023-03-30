package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {
    
    @Test
    @DisplayName("점수를 더한다.")
    void addScore() {
        Score score = new Score(3);

        assertThat(score.add(new Score(2.5))).isEqualTo(new Score(5.5));
    }

    @Test
    @DisplayName("점수를 뺀다.")
    void subtractScore() {
        Score score = new Score(3);

        assertThat(score.subtract(new Score(2.5))).isEqualTo(new Score(0.5));
    }

    @ParameterizedTest
    @DisplayName("점수를 비교한다.")
    @CsvSource(value = {"10:9:1", "9:10:-1", "10:10:0"}, delimiter = ':')
    void compare(int score1, int score2, int result) {
        Score whiteScore = new Score(score1);
        Score blackScore = new Score(score2);

        assertThat(whiteScore.compareTo(blackScore)).isEqualTo(result);
    }
}
