package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(10);
    }

    @Test
    @DisplayName("스코어 plus 메서드를 이용해서 더할 수 있다.")
    void plusTest() {
        Score other = new Score(1);

        Score result = score.plus(other);

        assertThat(result).isEqualTo(new Score(11));
    }

    @Test
    @DisplayName("스코어 value 메서드로 value 반환한다.")
    void valueTest() {
        double value = score.value();

        assertThat(value).isEqualTo(10d);
    }

    @Test
    @DisplayName("같은 라인에 같은색 폰이 있는 개수만큼 0.5점을 뺴준다.")
    void calculatePawnPenaltyScoreTest() {
        int count = 1;

        Score result = score.calculatePawnPenaltyScore(count);

        assertThat(result).isEqualTo(new Score(9.5d));
    }
}