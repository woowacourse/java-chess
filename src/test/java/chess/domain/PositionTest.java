package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @ParameterizedTest
    @ValueSource(chars = {'`', 'A', 'i'})
    @DisplayName("가로 위치가 범위를 벗어나면 예외발생")
    void outOfRawRangeException(char row) {
        assertThatThrownBy(() -> new Position(row, '1'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 위치는 a~h 범위에 포함되어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '0', '9'})
    @DisplayName("세로 위치가 범위를 벗어나면 예외발생")
    void outOfColumnRangeException(char column) {
        assertThatThrownBy(() -> new Position('a', column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세로 위치는 1~8 범위에 포함되어야 합니다.");
    }
}
