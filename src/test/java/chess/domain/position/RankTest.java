package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Nested
    class 생성 {

        @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8"})
        void 문자_1에서_8이_입력되었을때_정상생성(final String input) {
            //given

            //when

            //then
            assertThat(Rank.from(input)).isInstanceOf(Rank.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
        void 인덱스_1에서_8이_입력되었을때_정상생성(final int input) {
            //given

            //when

            //then
            assertThat(Rank.from(input)).isInstanceOf(Rank.class);
        }


        @ParameterizedTest
        @ValueSource(strings = {"", " ", "0", "9", "5.5", "SDdsf", "오"})
        void 문자_1에서_8외의값이_입력됐을때_예외를던진다(final String input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> Rank.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File은 1에서 8사이의 값 이어야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 9, 10})
        void 인덱스_1에서_8외의값이_입력됐을때_예외를던진다(final int input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> Rank.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("File은 1에서 8사이의 값 이어야 합니다.");
        }
    }

    @Nested
    class 거리계산 {

        @Test
        void 다른_file을_입력받았을때_거리를_반환한다() {
            //given
            Rank criteria = Rank.FIVE;
            Rank compare = Rank.FOUR;
            int expected = 1;

            //when
            int actual = criteria.calculateDistance(compare);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
    //TODO file.plus()
}
