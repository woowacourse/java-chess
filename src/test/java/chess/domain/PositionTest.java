package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"0:1", "1:0", "9:1", "1:9"}, delimiter = ':')
    @DisplayName("Position 은 각각 1 ~ 8을 넘길 수 없다.")
    void createPositionFail(int x, int y) {
        assertThatThrownBy(() -> new Position(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Position.EXCEPTION_MESSAGE_OUT_OF_BOUNDS);
    }

    @Test
    @DisplayName("Position 이 1 ~ 8 이내이면 생성이 이루어진다.")
    void createPositionSuccess() {
        assertDoesNotThrow(() -> new Position(1, 8));
    }
}
