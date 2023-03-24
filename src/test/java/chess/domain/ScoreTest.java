package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @Test
    void plus_메서드를_호출하면_두_Score의_합한_Score를_반환한다() {
        //given
        Score score1 = new Score(2.0);
        Score score2 = new Score(3.0);

        //when
        Score actual = score1.add(score2);

        //then
        assertThat(actual).isEqualTo(new Score(2.0 + 3.0));
    }

    @Test
    void multiply_메서드를_호출하면_Score에_횟수를_곱한_Score를_반환한다() {
        //given
        Score score = new Score(3.0);

        //when
        Score actual = score.multiply(2L);

        //then
        assertThat(actual).isEqualTo(new Score(3.0 * 2L));
    }

    @Nested
    class isMoreThan{

        @Test
        void criteria가_compare보다_큰_값을_가지면_true를_반환한다() {
            //given
            Score criteria = new Score(3.0);
            Score compare = new Score(2.0);

            //when
            boolean actual = criteria.isMoreThan(compare);

            //then
            assertThat(actual).isTrue();
        }

        @ParameterizedTest
        @ValueSource(doubles={3.0, 4.0})
        void criteria가_compare보다_작거나_같은_값을_가지면_false를_반환한다(Double input) {
            //given
            Score criteria = new Score(3.0);
            Score compare = new Score(input);

            //when
            boolean actual = criteria.isMoreThan(compare);

            //then
            assertThat(actual).isFalse();
        }
    }
}
