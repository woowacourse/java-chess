package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("위치값은 0부터 7까지 가능하다.")
    void validate(int value) {
        assertThatThrownBy(() -> new Position(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @DisplayName("입력받은 값 만큼 이동한다.")
    @Test
    void moveBy() {
        Position position = new Position(5);
        assertThat(position.moveBy(1)).isEqualTo(new Position(6));
        assertThat(position.moveBy(-1)).isEqualTo(new Position(4));
    }

    @DisplayName("값의 차이를 계산한다.")
    @Test
    void calculateDifference() {
        Position position = new Position(5);
        Position position1 = new Position(4);
        assertThat(position.calculateDifference(position1)).isEqualTo(-1);
    }
}
