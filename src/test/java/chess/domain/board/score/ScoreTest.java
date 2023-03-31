package chess.domain.board.score;

import chess.domain.board.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "0,1,1",
            "100000,3,100003"
    })
    @DisplayName("plus() : Score끼리 더할 수 있다.")
    void test_plus(final int value1, final int value2, final int resultValue) throws Exception {
        //given
        final Score score1 = Score.from(value1);
        final Score score2 = Score.from(value2);
        final Score result = Score.from(resultValue);

        //when & then
        assertEquals(score1.plus(score2), result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,2",
            "0,1,0",
            "100000,3,300000"
    })
    @DisplayName("multiply() : Score에 value만큼 곱할 수 있다.")
    void test_multiply(final int value1, final int repeat, final int resultValue) throws Exception {
        //given
        final Score score = Score.from(value1);
        final Score result = Score.from(resultValue);

        //when & then
        assertEquals(score.multiply(repeat), result);
    }

    @ParameterizedTest
    @CsvSource({
            "2,1",
            "1,0",
            "100000,3"
    })
    @DisplayName("isGreaterThan(value) : score가 value 보다 크면 true 를 반환한다.")
    void test_isGreaterThan(final int value1, final int value2) throws Exception {
        //given
        final Score score1 = Score.from(value1);
        final Score score2 = Score.from(value2);

        //when & then
        assertTrue(score1.isGreaterThan(score2));
        assertFalse(score2.isGreaterThan(score1));
    }
}
