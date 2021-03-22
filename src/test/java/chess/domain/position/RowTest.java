package chess.domain.position;

import chess.exception.InvalidRowException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RowTest {
    @Test
    @DisplayName("문자로 올바른 Row 반환")
    void fromSuccess() {
        assertThat(Row.from('1')).isEqualTo(Row.FIRST);
        assertThat(Row.from('8')).isEqualTo(Row.EIGHTH);
    }

    @ParameterizedTest(name = "올바르지 않은 문자는 Row 반환 실패")
    @ValueSource(chars = {'ㄱ', 'z', 'i', '0', '9'})
    void fromFail(Character input) {
        assertThatThrownBy(() -> Row.from(input))
                .isInstanceOf(InvalidRowException.class);
    }

    @ParameterizedTest(name = "해당 값만큼 떨어진 Row 반환")
    @MethodSource("nextRowSuccessTestcase")
    void nextRowSuccess(Row sourceRow, int moveValue, Row targetRow) {
        assertThat(sourceRow.nextRow(moveValue)).isEqualTo(targetRow);
    }

    private static Stream<Arguments> nextRowSuccessTestcase() {
        return Stream.of(
                Arguments.of(Row.FIRST, 0, Row.FIRST),
                Arguments.of(Row.FIRST, 1, Row.SECOND),
                Arguments.of(Row.FIRST, 7, Row.EIGHTH),
                Arguments.of(Row.EIGHTH, -1, Row.SEVENTH),
                Arguments.of(Row.EIGHTH, -7, Row.FIRST)
        );
    }

    @ParameterizedTest(name = "범위를 벗어나면 실패")
    @MethodSource("nextRowFailTestcase")
    void nextRowFail(Row sourceRow, int moveValue) {
        assertThatThrownBy(() -> sourceRow.nextRow(moveValue))
                .isInstanceOf(InvalidRowException.class);
    }

    private static Stream<Arguments> nextRowFailTestcase() {
        return Stream.of(
                Arguments.of(Row.FIRST, -1),
                Arguments.of(Row.FIRST, 8),
                Arguments.of(Row.EIGHTH, 1),
                Arguments.of(Row.EIGHTH, -8)
        );
    }
}