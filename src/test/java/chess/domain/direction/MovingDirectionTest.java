package chess.domain.direction;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovingDirectionTest {

    @Test
    @DisplayName("NORTH")
    void findDirectionNorth() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("c4"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH);
    }

    @Test
    @DisplayName("NORTH_EAST")
    void findDirectionNorthEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d4"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_EAST);
    }

    @Test
    @DisplayName("EAST")
    void findDirectionEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d3"));
        assertThat(direction).isEqualTo(MovingDirection.EAST);
    }

    @Test
    @DisplayName("SOUTH_EAST")
    void findDirectionSouthEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d2"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_EAST);
    }

    @Test
    @DisplayName("SOUTH")
    void findDirectionSouth() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("c2"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH);
    }

    @Test
    @DisplayName("SOUTH_WEST")
    void findDirectionSouthWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b2"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_WEST);
    }

    @Test
    @DisplayName("WEST")
    void findDirectionWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b3"));
        assertThat(direction).isEqualTo(MovingDirection.WEST);
    }

    @Test
    @DisplayName("NORTH_WEST")
    void findDirectionNorthWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b4"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_WEST);
    }

    @Test
    @DisplayName("NORTH_NORTH_EAST")
    void findDirectionNorthNorthEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d5"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_NORTH_EAST);
    }

    @Test
    @DisplayName("NORTH_EAST_EAST")
    void findDirectionNorthEastEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("e4"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_EAST_EAST);
    }

    @Test
    @DisplayName("SOUTH_EAST_EAST")
    void findDirectionSouthEastEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("e2"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_EAST_EAST);
    }

    @Test
    @DisplayName("SOUTH_SOUTH_EAST")
    void findDirectionSouthSouthEast() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("d1"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_SOUTH_EAST);
    }

    @Test
    @DisplayName("SOUTH_SOUTH_WEST")
    void findDirectionSouthSouthWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b1"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_SOUTH_WEST);
    }

    @Test
    @DisplayName("SOUTH_WEST_WEST")
    void findDirectionSouthWestWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("a2"));
        assertThat(direction).isEqualTo(MovingDirection.SOUTH_WEST_WEST);
    }

    @Test
    @DisplayName("NORTH_WEST_WEST")
    void findDirectionNorthWestWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("a4"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_WEST_WEST);
    }

    @Test
    @DisplayName("NORTH_NORTH_WEST")
    void findDirectionNorthNorthWest() {
        MovingDirection direction = MovingDirection.getDirection(Position.of("c3"), Position.of("b5"));
        assertThat(direction).isEqualTo(MovingDirection.NORTH_NORTH_WEST);
    }

    @Test
    @DisplayName("체스 움직임 예외")
    void findDirectionException() {
        assertThatThrownBy(() -> MovingDirection.getDirection(Position.of("c3"), Position.of("f4")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}