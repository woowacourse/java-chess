package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.exception.PositionMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("정상적인 위치면 예외를 발생시키지 않는다.")
    void throws_not_exception_when_case_is_normally() {
        // given
        Position position = Position.from("b2");

        // when & then
        Assertions.assertAll(
                () -> assertThat(position.getCol()).isEqualTo('b'),
                () -> assertThat(position.getRow()).isEqualTo('2')
        );
    }

    @ParameterizedTest(name = "Column {0} is invalid")
    @ValueSource(strings = {"m1", "z2", "x6"})
    @DisplayName("잘못된 Column이면 예외를 발생시킨다.")
    void throws_exception_when_case_column_is_invalid(final String input) {
        // when & then
        assertThatThrownBy(() -> Position.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PositionMessage.INVALID_COLUMN.getMessage());
    }

    @ParameterizedTest(name = "Row {0} is invalid")
    @ValueSource(strings = {"a0", "c9", "d0"})
    @DisplayName("잘못된 Row면 예외를 발생시킨다.")
    void throws_exception_when_case_row_is_invalid(final String input) {
        // when & then
        assertThatThrownBy(() -> Position.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PositionMessage.INVALID_ROW.getMessage());
    }
}
