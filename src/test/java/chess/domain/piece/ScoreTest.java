package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(10);
    }

    @Test
    @DisplayName("스코어 plus 메서드를 이용해서 더할 수 있다.")
    void sumTest() {
        //given
        Score other = new Score(1);

        //when
        Score result = score.sum(other);

        //then
        assertThat(result).isEqualTo(new Score(11));
    }

    @Test
    @DisplayName("Score 객체의 value 반환한다.")
    void valueTest() {
        //given
        double value = score.value();

        //then
        assertThat(value).isEqualTo(10d);
    }

    @Test
    @DisplayName("같은 라인에 같은색 폰이 있는 개수만큼 0.5점을 뺴준다.")
    void calculatePawnPenaltyScoreTest() {
        //given
        int count = 1;
        Score result = score.calculatePawnPenaltyScore(count);

        //then
        assertThat(result).isEqualTo(new Score(9.5d));
    }

    @Test
    @DisplayName("Score 객체끼리 대소 관계 비교 가능하다.")
    void isHigherThanTest() {
        //given
        Score oneScore = new Score(1);
        Score elevenScore = new Score(11);

        //when
        boolean isHigher = score.isHigherThan(oneScore);
        boolean isLower = score.isHigherThan(elevenScore);

        //then
        assertThat(isHigher).isTrue();
        assertThat(isLower).isFalse();
    }
}