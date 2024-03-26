package domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    Direction direction;

    @BeforeEach
    void setUp() {
        // default 메서드에 대한 테스트이므로, 방향은 임의로 선택
        direction = DiagonalDirection.DOWN_LEFT;
    }

    @DisplayName("입력된 값이 0인 경우 0을 그대로 반환한다.")
    @Test
    void normalizeZero() {
        assertThat(direction.normalize(0)).isEqualTo(0);
    }

    @DisplayName("입력된 값이 양수인 경우 1로 정규화한다.")
    @Test
    void normalizePositive() {
        assertThat(direction.normalize(7)).isEqualTo(1);
    }

    @DisplayName("입력된 값이 음수인 경우 -1로 정규화한다.")
    @Test
    void normalizeNegative() {
        assertThat(direction.normalize(-7)).isEqualTo(-1);
    }
}