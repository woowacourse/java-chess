package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    void moveUp() {
        assertThat(Direction.UP.move(CoordinatePair.of("a1").get()))
            .isEqualTo(CoordinatePair.of("a2"));
        assertThat(Direction.UP.move(CoordinatePair.of("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveDown() {
        assertThat(Direction.DOWN.move(CoordinatePair.of("g5").get()))
            .isEqualTo(CoordinatePair.of("g4"));
        assertThat(Direction.DOWN.move(CoordinatePair.of("g1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeft() {
        assertThat(Direction.LEFT.move(CoordinatePair.of("c2").get()))
            .isEqualTo(CoordinatePair.of("b2"));
        assertThat(Direction.LEFT.move(CoordinatePair.of("a1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRight() {
        assertThat(Direction.RIGHT.move(CoordinatePair.of("d7").get()))
            .isEqualTo(CoordinatePair.of("e7"));
        assertThat(Direction.RIGHT.move(CoordinatePair.of("h7").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftTop() {
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.of("c4").get()))
            .isEqualTo(CoordinatePair.of("b5"));
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.of("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.of("a8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.of("h8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightTop() {
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.of("a1").get()))
            .isEqualTo(CoordinatePair.of("b2"));
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.of("h8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.of("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.of("c8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftBottom() {
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.of("d4").get()))
            .isEqualTo(CoordinatePair.of("c3"));
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.of("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.of("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.of("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightBottom() {
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.of("d4").get()))
            .isEqualTo(CoordinatePair.of("e3"));
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.of("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.of("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.of("h8").get()))
            .isEqualTo(Optional.empty());
    }
}
