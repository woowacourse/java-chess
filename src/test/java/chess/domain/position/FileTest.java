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
        void should_정상생성_when_a에서h가_입력되었을때(final String input) {
            //given

            //when

            //then
            assertThat(File.valueOf(input.toUpperCase())).isInstanceOf(File.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "i", "0", "z", "aa", "오"})
        void should_예외를던진다_when_a에서h외의값이_입력됐을때(final String input) {
            //given

            //when
            final ThrowingCallable throwingCallable = () -> File.valueOf(input.toUpperCase());

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 거리계산 {
        @Test
        void should_거리를_반환한다_when_다른_rank를_받았을_때() {
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
}