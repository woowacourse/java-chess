package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    @DisplayName("시계방향으로 돌면서 다음 방향을 되돌려준다.")
    void clockwiseNext() {
        assertThat(Direction.EAST.clockwiseNext()).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    @DisplayName("반시계방향으로 돌면서 다음 방향을 되돌려준다.")
    void counterclockwiseNext() {
        assertThat(Direction.EAST.counterclockwiseNext()).isEqualTo(Direction.NORTH_EAST);
    }
}