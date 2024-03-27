package domain.coordinate.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("움직일 수 있는 범위를 벗어날 수 없다.")
    void overRangePosition(int distance) {
        Position position = new Position(distance);

        assertThatThrownBy(() -> position.next(distance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 위치가 아닙니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -1, 0})
    @DisplayName("주어진 거리만큼 이동한다")
    void moveBy(int distance) {
        Position position = new Position(2);

        Position nextPosition = position.next(distance);
        Position expectedPosition = new Position(2 + distance);

        assertThat(nextPosition).isEqualTo(expectedPosition);
    }
}
