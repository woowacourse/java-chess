package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void createPosition() {
        assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("위치는 가로, 세로 범위는 각각 1 ~ 8 이다.")
    @CsvSource({"0,1", "1,0", "1, 9", "9, 1"})
    void createPositionThrowException(int row, int column) {
        assertThatThrownBy(() -> new Position(row, column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
    }
}
