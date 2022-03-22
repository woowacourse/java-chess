package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;


class LineNumberTest {

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("number로 위치를 생성할 수 있다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void createLineNumberByString(final int number) {
        assertThatCode(() -> LineNumber.of(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("문자열로 위치를 생성할 수 있다.")
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6","7", "8"})
    void createLineNumberByString(final String input) {
        assertThatCode(() -> LineNumber.of(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("라인 번호는 1~8사이여야 한다.")
    @ValueSource(ints = {0, 9, 10})
    void throwsExceptionWithNumberOutOfRange(final int number) {
        assertThatThrownBy(() -> LineNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값에 의해 비교할 수 있다.")
    void testIfEqualsByValue() {
        LineNumber aNumber = LineNumber.of(3);
        LineNumber bNumber = LineNumber.of(3);

        assertThat(aNumber).isEqualTo(bNumber);
    }
}
