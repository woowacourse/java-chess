package model;

import model.Direction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void clockwiseNext() {
        assertThat(Direction.EAST.clockwiseNext()).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    void counterclockwiseNext() {
        assertThat(Direction.EAST.counterclockwiseNext()).isEqualTo(Direction.NORTH_EAST);
    }
}