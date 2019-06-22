package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    void moveUp() {
        assertThat(Direction.UP.move(CoordinatePair.from("a1").get()))
            .isEqualTo(CoordinatePair.from("a2"));
        assertThat(Direction.UP.move(CoordinatePair.from("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveDown() {
        assertThat(Direction.DOWN.move(CoordinatePair.from("g5").get()))
            .isEqualTo(CoordinatePair.from("g4"));
        assertThat(Direction.DOWN.move(CoordinatePair.from("g1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeft() {
        assertThat(Direction.LEFT.move(CoordinatePair.from("c2").get()))
            .isEqualTo(CoordinatePair.from("b2"));
        assertThat(Direction.LEFT.move(CoordinatePair.from("a1").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRight() {
        assertThat(Direction.RIGHT.move(CoordinatePair.from("d7").get()))
            .isEqualTo(CoordinatePair.from("e7"));
        assertThat(Direction.RIGHT.move(CoordinatePair.from("h7").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftTop() {
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.from("c4").get()))
            .isEqualTo(CoordinatePair.from("b5"));
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.from("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.from("a8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_TOP.move(CoordinatePair.from("h8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightTop() {
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.from("a1").get()))
            .isEqualTo(CoordinatePair.from("b2"));
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.from("h8").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.from("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_TOP.move(CoordinatePair.from("c8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftBottom() {
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.from("d4").get()))
            .isEqualTo(CoordinatePair.from("c3"));
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.from("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.from("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.LEFT_BOTTOM.move(CoordinatePair.from("a8").get()))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightBottom() {
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.from("d4").get()))
            .isEqualTo(CoordinatePair.from("e3"));
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.from("a1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.from("h1").get()))
            .isEqualTo(Optional.empty());
        assertThat(Direction.RIGHT_BOTTOM.move(CoordinatePair.from("h8").get()))
            .isEqualTo(Optional.empty());
    }
}
