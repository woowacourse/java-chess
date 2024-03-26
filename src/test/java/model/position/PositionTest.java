package model.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @DisplayName("유효하지 않은 위치인 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {"aa1", "abb", "h", "11", "aa33", "a1.", ".a1"})
    void invalidPosition(String value) {
        assertThatThrownBy(() -> Position.from(value))
                .isInstanceOf(InvalidPositionException.class);
    }
}
