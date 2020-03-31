package domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    @DisplayName("isMovableLimited 테스트")
    void isMovableLimited_True() {
        assertThat(Direction.TOP.isMovableLimited(1, 0)).isTrue();
        assertThat(Direction.RIGHT_TOP.isMovableLimited(1, 1)).isTrue();
        assertThat(Direction.RIGHT.isMovableLimited(0, 1)).isTrue();
        assertThat(Direction.RIGHT_DOWN.isMovableLimited(-1, 1)).isTrue();
        assertThat(Direction.DOWN.isMovableLimited(-1, 0)).isTrue();
        assertThat(Direction.LEFT_DOWN.isMovableLimited(-1, -1)).isTrue();
        assertThat(Direction.LEFT.isMovableLimited(0, -1)).isTrue();
        assertThat(Direction.LEFT_TOP.isMovableLimited(1, -1)).isTrue();
        assertThat(Direction.TOP_TOP_RIGHT.isMovableLimited(2, 1)).isTrue();
        assertThat(Direction.RIGHT_RIGHT_TOP.isMovableLimited(1, 2)).isTrue();
        assertThat(Direction.RIGHT_RIGHT_DOWN.isMovableLimited(-1, 2)).isTrue();
        assertThat(Direction.DOWN_DOWN_RIGHT.isMovableLimited(-2, 1)).isTrue();
        assertThat(Direction.DOWN_DOWN_LEFT.isMovableLimited(-2, -1)).isTrue();
        assertThat(Direction.LEFT_LEFT_DOWN.isMovableLimited(-1, -2)).isTrue();
        assertThat(Direction.LEFT_LEFT_TOP.isMovableLimited(1, -2)).isTrue();
        assertThat(Direction.TOP_TOP_LEFT.isMovableLimited(2, -1)).isTrue();

    }

    @Test
    @DisplayName("isMovableUnlimited 테스트")
    void isMovableUnlimited_True() {
        assertThat(Direction.TOP.isMovableUnlimited(4, 0)).isTrue();
        assertThat(Direction.RIGHT_TOP.isMovableUnlimited(5, 5)).isTrue();
        assertThat(Direction.RIGHT.isMovableUnlimited(0, 6)).isTrue();
        assertThat(Direction.RIGHT_DOWN.isMovableUnlimited(-4, 4)).isTrue();
        assertThat(Direction.DOWN.isMovableUnlimited(-3, 0)).isTrue();
        assertThat(Direction.LEFT_DOWN.isMovableUnlimited(-6, -6)).isTrue();
        assertThat(Direction.LEFT.isMovableUnlimited(0, -2)).isTrue();
        assertThat(Direction.LEFT_TOP.isMovableUnlimited(1, -1)).isTrue();
    }

    @Test
    @DisplayName("isMovableLimited false 테스트")
    void isMovableLimited_False() {
        assertThat(Direction.TOP.isMovableLimited(1, 1)).isFalse();
        assertThat(Direction.RIGHT_TOP.isMovableLimited(1, 0)).isFalse();
        assertThat(Direction.RIGHT.isMovableLimited(0, -1)).isFalse();
        assertThat(Direction.RIGHT_DOWN.isMovableLimited(-1, 0)).isFalse();
        assertThat(Direction.DOWN.isMovableLimited(-1, 1)).isFalse();
        assertThat(Direction.LEFT_DOWN.isMovableLimited(-1, 0)).isFalse();
        assertThat(Direction.LEFT.isMovableLimited(1, -1)).isFalse();
        assertThat(Direction.LEFT_TOP.isMovableLimited(-1, -1)).isFalse();
        assertThat(Direction.TOP_TOP_RIGHT.isMovableLimited(2, -1)).isFalse();
        assertThat(Direction.RIGHT_RIGHT_TOP.isMovableLimited(-1, 2)).isFalse();
        assertThat(Direction.RIGHT_RIGHT_DOWN.isMovableLimited(-1, -2)).isFalse();
        assertThat(Direction.DOWN_DOWN_RIGHT.isMovableLimited(-2, 0)).isFalse();
        assertThat(Direction.DOWN_DOWN_LEFT.isMovableLimited(-2, 0)).isFalse();
        assertThat(Direction.LEFT_LEFT_DOWN.isMovableLimited(-1, -1)).isFalse();
        assertThat(Direction.LEFT_LEFT_TOP.isMovableLimited(1, -1)).isFalse();
        assertThat(Direction.TOP_TOP_LEFT.isMovableLimited(2, 0)).isFalse();
    }

    @Test
    @DisplayName("isMovableUnlimited false 테스트")
    void isMovableUnlimited_False() {
        assertThat(Direction.TOP.isMovableUnlimited(4, 1)).isFalse();
        assertThat(Direction.RIGHT_TOP.isMovableUnlimited(4, 5)).isFalse();
        assertThat(Direction.RIGHT.isMovableUnlimited(1, 6)).isFalse();
        assertThat(Direction.RIGHT_DOWN.isMovableUnlimited(-5, 4)).isFalse();
        assertThat(Direction.DOWN.isMovableUnlimited(-3, 1)).isFalse();
        assertThat(Direction.LEFT_DOWN.isMovableUnlimited(-5, -6)).isFalse();
        assertThat(Direction.LEFT.isMovableUnlimited(1, -2)).isFalse();
        assertThat(Direction.LEFT_TOP.isMovableUnlimited(2, -1)).isFalse();
    }
}
