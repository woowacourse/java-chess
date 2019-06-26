package model.board;

import org.junit.jupiter.api.Test;

import static model.board.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {
    @Test
    void rotationCWTest1() {
        assertThat(NORTH.rotateClockwise(11)).isEqualTo(SOUTH_EAST);
    }

    @Test
    void rotationCWTest2() {
        assertThat(NORTH.rotateClockwise(1)).isEqualTo(NORTH_EAST);
    }

    @Test
    void rotationCWTest3() {
        assertThat(NORTH.rotateClockwise(-3)).isEqualTo(SOUTH_WEST);
    }

    @Test
    void rotationCCWTest1() {
        assertThat(NORTH.rotateCounterClockwise(45)).isEqualTo(SOUTH_EAST);
    }

    @Test
    void rotationCCWTest2() {
        assertThat(NORTH.rotateCounterClockwise(1)).isEqualTo(NORTH_WEST);
    }

    @Test
    void rotationCCWTest3() {
        assertThat(NORTH.rotateCounterClockwise(-11)).isEqualTo(SOUTH_EAST);
    }
}