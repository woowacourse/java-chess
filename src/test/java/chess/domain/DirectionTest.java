package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void 방향확인() {
        assertThat(Direction.WEST.isSameDirection(-2, 0)).isTrue();
        assertThat(Direction.WEST.isSameDirection(2, 0)).isFalse();
        assertThat(Direction.WEST.isSameDirection(2, 2)).isFalse();
        assertThat(Direction.WEST.isSameDirection(2, -1)).isFalse();
    }

}