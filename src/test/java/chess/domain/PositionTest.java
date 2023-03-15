package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @DisplayName("가로는 0미만일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -4, -3, -2, -1})
    void throwExceptionWhenRowIsLessThenZero(final int row) {
        assertThatThrownBy(() -> new Position(row, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("세로는 0미만일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -4, -3, -2, -1})
    void throwExceptionWhenColumnIsLessThenZero(final int column) {
        assertThatThrownBy(() -> new Position(0, column))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
