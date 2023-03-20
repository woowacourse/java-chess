package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Rank;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Nested
    class 생성 {


        @ParameterizedTest
        @ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
        void 문자_a에서_h가_입력되었을때_정상생성(final String input) {
            //given

            //when

            //then
            assertThat(Rank.from(input)).isInstanceOf(Rank.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "i", "0", "z", "aa", "오"})
        void 문자_a에서_h외의값이_입력됐을때_예외를던진다(final String input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> Rank.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank는 a에서 h사이의 값 이어야 합니다.");
        }
    }

    @Nested
    class 거리계산 {

        @Test
        void 다른_rank를_받았을_때_거리를_반환한다() {
            //given
            Rank criteria = Rank.E;
            Rank compare = Rank.D;
            int expected = 1;

            //when
            int actual = criteria.calculateDistance(compare);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
