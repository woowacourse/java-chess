package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    void moveUp() {

        assertThat(CoordinatePair.of("a1").get().move(Direction.UP))
            .isEqualTo(CoordinatePair.of("a2"));
        assertThat(CoordinatePair.of("a8").get().move(Direction.UP))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveDown() {
        assertThat(CoordinatePair.of("g5").get().move(Direction.DOWN))
            .isEqualTo(CoordinatePair.of("g4"));
        assertThat(CoordinatePair.of("g1").get().move(Direction.DOWN))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeft() {
        assertThat(CoordinatePair.of("c2").get().move(Direction.LEFT))
            .isEqualTo(CoordinatePair.of("b2"));
        assertThat(CoordinatePair.of("a1").get().move(Direction.LEFT))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRight() {
        assertThat(CoordinatePair.of("d7").get().move(Direction.RIGHT))
            .isEqualTo(CoordinatePair.of("e7"));
        assertThat(CoordinatePair.of("h7").get().move(Direction.RIGHT))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftTop() {
        assertThat(CoordinatePair.of("c4").get().move(Direction.LEFT_TOP))
            .isEqualTo(CoordinatePair.of("b5"));
        assertThat(CoordinatePair.of("a1").get().move(Direction.LEFT_TOP))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("a8").get().move(Direction.LEFT_TOP))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("h8").get().move(Direction.LEFT_TOP))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightTop() {
        assertThat(CoordinatePair.of("a1").get().move(Direction.RIGHT_TOP))
            .isEqualTo(CoordinatePair.of("b2"));
        assertThat(CoordinatePair.of("h8").get().move(Direction.RIGHT_TOP))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("h1").get().move(Direction.RIGHT_TOP))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("c8").get().move(Direction.RIGHT_TOP))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveLeftBottom() {
        assertThat(CoordinatePair.of("d4").get().move(Direction.LEFT_BOTTOM))
            .isEqualTo(CoordinatePair.of("c3"));
        assertThat(CoordinatePair.of("a1").get().move(Direction.LEFT_BOTTOM))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("h1").get().move(Direction.LEFT_BOTTOM))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("a8").get().move(Direction.LEFT_BOTTOM))
            .isEqualTo(Optional.empty());
    }

    @Test
    void moveRightBottom() {
        assertThat(CoordinatePair.of("d4").get().move(Direction.RIGHT_BOTTOM))
            .isEqualTo(CoordinatePair.of("e3"));
        assertThat(CoordinatePair.of("a1").get().move(Direction.RIGHT_BOTTOM))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("h1").get().move(Direction.RIGHT_BOTTOM))
            .isEqualTo(Optional.empty());
        assertThat(CoordinatePair.of("h8").get().move(Direction.RIGHT_BOTTOM))
            .isEqualTo(Optional.empty());
    }
}
