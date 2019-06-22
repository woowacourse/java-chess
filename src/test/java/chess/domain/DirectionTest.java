package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    void moveUp() {
        assertThat(Direction.UP.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(CoordinatePair.valueOf("a2"));
        assertThat(Direction.UP.move(CoordinatePair.valueOf("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveDown() {
        assertThat(Direction.DOWN.move(CoordinatePair.valueOf("g5").get()))
            .isEqualTo(CoordinatePair.valueOf("g4"));
        assertThat(Direction.DOWN.move(CoordinatePair.valueOf("g1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeft() {
        assertThat(Direction.LEFT.move(CoordinatePair.valueOf("c2").get()))
            .isEqualTo(CoordinatePair.valueOf("b2"));
        assertThat(Direction.LEFT.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRight() {
        assertThat(Direction.RIGHT.move(CoordinatePair.valueOf("d7").get()))
            .isEqualTo(CoordinatePair.valueOf("e7"));
        assertThat(Direction.RIGHT.move(CoordinatePair.valueOf("h7").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftTop() {
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.valueOf("c4").get()))
            .isEqualTo(CoordinatePair.valueOf("b5"));
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.valueOf("a8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.valueOf("h8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightTop() {
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(CoordinatePair.valueOf("b2"));
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.valueOf("h8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.valueOf("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.valueOf("c8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftBottom() {
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.valueOf("d4").get()))
            .isEqualTo(CoordinatePair.valueOf("c3"));
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.valueOf("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.valueOf("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightBottom() {
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.valueOf("d4").get()))
            .isEqualTo(CoordinatePair.valueOf("e3"));
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.valueOf("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.valueOf("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.valueOf("h8").get()))
            .isEqualTo(Optional.empty());
    }
}
