package position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("움직일 수 있는 범위를 벗어날 수 없다.")
    void overRangePosition(int nextPosition) {
        Position position = new Position(nextPosition);

        Assertions.assertThatThrownBy(() -> position.move(nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 위치가 아닙니다.");
    }
}
