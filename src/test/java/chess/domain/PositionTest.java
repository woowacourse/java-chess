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
        assertThatThrownBy(() -> new Position(row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 위치는 a~h 범위에 포함되어야 합니다.");
    }
}
