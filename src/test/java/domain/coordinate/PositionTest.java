package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @DisplayName("입력받은 값 만큼 이동한다.")
    @Test
    void moveBy() {
        Position position = Position.of(5);
        assertThat(position.moveBy(1)).isEqualTo(Position.of(6));
        assertThat(position.moveBy(-1)).isEqualTo(Position.of(4));
    }

    @ParameterizedTest
    @ValueSource(ints = {-6, 3})
    @DisplayName("위치 값의 범위를 초과하여 이동할 수 없다.")
    void moveByException(int offset) {
        Position position = Position.of(5);
        assertThatThrownBy(() -> position.moveBy(offset))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("값의 차이를 계산한다.")
    @Test
    void calculateDifference() {
        Position position = Position.of(5);
        Position position1 = Position.of(4);
        assertThat(position.calculateDifference(position1)).isEqualTo(-1);
    }
}
