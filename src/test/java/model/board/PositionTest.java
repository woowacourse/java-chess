package model.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @Test
    void parseFromTextTest() {
        Position pos = Position.of("b4");
        assertThat(pos.x()).isEqualTo(Coord.of(1));
        assertThat(pos.y()).isEqualTo(Coord.of(3));
    }

    @Test
    void overflowTest() {
        assertThatThrownBy(() -> Position.ofSafe("j9").orElseThrow(IllegalArgumentException::new));
    }

    @Test
    void testForwardTest() {
        assertThat(Position.of("b6").testForward(Direction.EAST)).isTrue();
    }

    @Test
    void moveForwardTest() {
        Position testPosition = Position.of("a1");
        Position expectedPosition = Position.of("a2");
        assertThat(testPosition.moveForward(Direction.NORTH)).isEqualTo(expectedPosition);
    }

    @Test
    void invalidForwardMovementTest() {
        assertThatThrownBy(
                () -> Position.of("h4").moveForwardSafe(Direction.EAST).orElseThrow(IllegalArgumentException::new)
        );
    }

    @Test
    void moveTest() {
        assertThat(Position.of("e6").move(2, -4).get()).isEqualTo(Position.of("g2"));
    }

    @Test
    void toStringTest() {
        assertThat(Position.of("c6").toString()).isEqualTo("c6");
    }

    @Test
    void equalityTest() {
        assertThat(Position.ofSafe("c4").map(pos -> Position.of("c4").equals(pos)).orElse(false)).isTrue();
    }

    @Test
    void sortTest() {
        assertThat(
                Stream.of(Position.of("f2"), Position.of("a1"), Position.of("b8")).sorted()
        ).isEqualTo(
                Arrays.asList(Position.of("a1"), Position.of("f2"), Position.of("b8"))
        );
    }
}