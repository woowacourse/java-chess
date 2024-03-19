package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {
    @DisplayName("가로가 범위 밖을 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void invalidRowThrowsException(int row) {
        assertThatThrownBy(() -> Position.of(row, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로는 1부터 8 사이의 값이어야 합니다: " + row);
    }

    @DisplayName("가로가 범위 밖을 벗어나면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void invalidColumnThrowsException(int column) {
        assertThatThrownBy(() -> Position.of(1, column))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세로는 1부터 8 사이의 값이어야 합니다: " + column);
    }
}
