package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("위치값을 받아 포지션을 생성한다.")
    void create() {
        assertThat(Position.from("a1")).isEqualTo(Position.of(Column.A, Row.ONE));
    }

    @Test
    @DisplayName("동쪽으로 이동한다.")
    void moveEast() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.EAST)).isEqualTo(Position.of(Column.E, Row.FOUR));
    }

    @Test
    @DisplayName("서쪽으로 이동한다.")
    void moveWest() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.WEST)).isEqualTo(Position.of(Column.C, Row.FOUR));
    }

    @Test
    @DisplayName("북쪽으로 이동한다.")
    void moveNorth() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.NORTH)).isEqualTo(Position.of(Column.D, Row.FIVE));
    }

    @Test
    @DisplayName("남쪽으로 이동한다.")
    void moveSouth() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.SOUTH)).isEqualTo(Position.of(Column.D, Row.THREE));
    }

    @Test
    @DisplayName("북동쪽으로 이동한다.")
    void moveNorthEast() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.NORTH_EAST)).isEqualTo(Position.of(Column.E, Row.FIVE));
    }

    @Test
    @DisplayName("북서쪽으로 이동한다.")
    void moveNorthWest() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.NORTH_WEST)).isEqualTo(Position.of(Column.C, Row.FIVE));
    }

    @Test
    @DisplayName("남동쪽으로 이동한다.")
    void moveSouthEast() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.SOUTH_EAST)).isEqualTo(Position.of(Column.E, Row.THREE));
    }

    @Test
    @DisplayName("남서쪽으로 이동한다.")
    void moveSouthWest() {
        Position position = Position.from("d4");
        assertThat(position.move(Direction.SOUTH_WEST)).isEqualTo(Position.of(Column.C, Row.THREE));
    }
}
