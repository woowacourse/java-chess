package model.board;

import org.junit.jupiter.api.Test;

import static model.board.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {
    @Test
    void rotationCWTest() {
        assertThat(NORTH.rotateClockwise(11)).isEqualTo(SOUTH_EAST);
    }

    @Test
    void rotationCWTest2() {
        assertThat(NORTH.rotateClockwise(1)).isEqualTo(NORTH_EAST);
    }

    @Test
    void rotationCCWTest() {
        assertThat(NORTH.rotateCounterClockwise(5)).isEqualTo(SOUTH_EAST);
    }

    @Test
    void rotationCCW2Test() {
        assertThat(NORTH.rotateCounterClockwise(1)).isEqualTo(NORTH_WEST);
    }
}