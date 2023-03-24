package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @Nested
    class 생성 {


        @ParameterizedTest
        @ValueSource(strings = {"a", "b", "c", "d", "e", "f", "g", "h"})
        void 문자_a에서_h가_입력되었을때_정상생성(final String input) {
            //given

            //when

            //then
            assertThat(File.from(input)).isInstanceOf(File.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
        void 인덱스_1에서_8이_입력되었을때_정상생성(final int input) {
            //given

            //when

            //then
            assertThat(File.from(input)).isInstanceOf(File.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "i", "0", "z", "aa", "오"})
        void 문자_a에서_h외의값이_입력됐을때_예외를던진다(final String input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> File.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank는 a에서 h사이의 값 이어야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(ints = {-1,0,9,10})
        void 인덱스_1에서_8외의값이_입력됐을때_예외를던진다(final int input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> File.from(input);

            //then
            assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rank는 a에서 h사이의 값 이어야 합니다.");
        }
    }

    @Nested
    class 거리계산 {

        @Test
        void 다른_file을_받았을_때_거리를_반환한다() {
            //given
            File criteria = File.E;
            File compare = File.D;
            int expected = 1;

            //when
            int actual = criteria.calculateDistance(compare);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void 다른_정수값을_받았을_때_그만큼_이동한_좌표_File을_반환한다(){
        //given
        File criteria = File.B;
        File expected = File.E;

        //when
        File actual = criteria.plus(3);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}
