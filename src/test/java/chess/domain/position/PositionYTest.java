package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionYTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("범위를 벗어난 좌표에 대해 예외를 처리하는지")
    void outOfRangeException(int coordination) {
        assertThatThrownBy(() -> PositionY.of(coordination))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "a"})
    @DisplayName("존재하지 않는 이름으로 탐색할 경우 예외를 처리하는지")
    void invalidNameException(String name) {
        assertThatThrownBy(() -> PositionY.of(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
