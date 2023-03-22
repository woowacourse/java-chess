package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    
    @Test
    @DisplayName("Score 객체를 생성한다.")
    void createScore() {
        Score score = Score.from(1.0);
        Assertions.assertThat(score).isEqualTo(Score.from(1.0));
    }
    
    @Test
    @DisplayName("Score 객체를 더한다.")
    void addScore() {
        Score score = Score.from(1.0);
        Score score2 = Score.from(2.0);
        Assertions.assertThat(score.add(score2)).isEqualTo(Score.from(3.0));
    }
    
    @Test
    @DisplayName("Score 객체를 곱한다.")
    void multiplyScore() {
        Score score = Score.from(1.0);
        Assertions.assertThat(score.multiply(2.0)).isEqualTo(Score.from(2.0));
    }
    
    @Test
    @DisplayName("Score 객체를 뺀다.")
    void subtractScore() {
        Score score = Score.from(1.0);
        Score score2 = Score.from(2.0);
        Assertions.assertThat(score.subtract(score2)).isEqualTo(Score.from(-1.0));
    }
    
    @Test
    @DisplayName("Score 객체를 비교한다.")
    void compareScore() {
        Score score = Score.from(1.0);
        Score score2 = Score.from(2.0);
        Assertions.assertThat(score.isBiggerThan(score2)).isFalse();
    }
    
    @Test
    @DisplayName("Score 객체를 반환한다.")
    void getScore() {
        Score score = Score.from(1.0);
        Assertions.assertThat(score.getScore()).isEqualTo(1.0);
    }
    
}