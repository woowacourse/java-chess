package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LineNumberTest {

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("number로 위치를 생성할 수 있다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    void createLineNumberByString(final int number) {
        assertThatCode(() -> LineNumber.of(number))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("캐릭터로 위치를 생성할 수 있다.")
    @ValueSource(chars = {'1', '2', '3', '4', '5', '6', '7', '8'})
    void createLineNumberByChar(final char input) {
        assertThatCode(() -> LineNumber.of(input))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{index} {displayName} input = {0}")
    @DisplayName("알파벳 캐릭터로 위치를 생성할 수 있다.")
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    void createLineNumberByAlphabet(final char input) {
        assertThatCode(() -> LineNumber.ofAlphabet(input))
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
