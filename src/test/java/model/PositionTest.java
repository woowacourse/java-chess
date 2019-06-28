package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("방향을 받아서 해당 방향에 있는 Position을 알려준다.")
    void PositionOfSpecificDirectionTest() {
        Position position = Position.of(5, 5);
        assertThat(position.of(Direction.NORTH)).isEqualTo(Position.of(5, 6));
        assertThat(position.of(Direction.NORTH_EAST)).isEqualTo(Position.of(6, 6));
        assertThat(position.of(Direction.EAST)).isEqualTo(Position.of(6, 5));
        assertThat(position.of(Direction.SOUTH_EAST)).isEqualTo(Position.of(6, 4));
        assertThat(position.of(Direction.SOUTH)).isEqualTo(Position.of(5, 4));
        assertThat(position.of(Direction.SOUTH_WEST)).isEqualTo(Position.of(4, 4));
        assertThat(position.of(Direction.WEST)).isEqualTo(Position.of(4, 5));
        assertThat(position.of(Direction.NORTH_WEST)).isEqualTo(Position.of(4, 6));
    }

    @Test
    @DisplayName("보드 바깥에서는 invalid")
    void outOfBoardTest() {
        assertThat(Position.of(0, -1).isValid()).isFalse();
    }
}