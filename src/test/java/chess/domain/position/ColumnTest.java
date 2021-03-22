package chess.domain.position;

import chess.exception.InvalidColumnException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColumnTest {
    @Test
    @DisplayName("문자로 올바른 Column 반환")
    void fromSuccess() {
        assertThat(Column.from('a')).isEqualTo(Column.A);
        assertThat(Column.from('h')).isEqualTo(Column.H);
    }

    @ParameterizedTest(name = "올바르지 않은 문자는 Column 반환 실패")
    @ValueSource(chars = {'ㄱ', 'z', 'i', '0', '1'})
    void fromFail(Character input) {
        assertThatThrownBy(() -> Column.from(input))
                .isInstanceOf(InvalidColumnException.class);
    }

    @ParameterizedTest(name = "해당 값만큼 떨어진 Column 반환")
    @MethodSource("nextColumnSuccessTestcase")
    void nextColumnSuccess(Column sourceColumn, int moveValue, Column targetColumn) {
        assertThat(sourceColumn.nextColumn(moveValue)).isEqualTo(targetColumn);
    }

    private static Stream<Arguments> nextColumnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Column.A, 0, Column.A),
                Arguments.of(Column.A, 1, Column.B),
                Arguments.of(Column.A, 7, Column.H),
                Arguments.of(Column.H, -1, Column.G),
                Arguments.of(Column.H, -7, Column.A)
        );
    }

    @ParameterizedTest(name = "범위를 벗어나면 실패")
    @MethodSource("nextColumnFailTestcase")
    void nextColumnFail(Column sourceColumn, int moveValue) {
        assertThatThrownBy(() -> sourceColumn.nextColumn(moveValue))
                .isInstanceOf(InvalidColumnException.class);
    }

    private static Stream<Arguments> nextColumnFailTestcase() {
        return Stream.of(
                Arguments.of(Column.A, -1),
                Arguments.of(Column.A, 8),
                Arguments.of(Column.H, 1),
                Arguments.of(Column.H, -8)
        );
    }
}