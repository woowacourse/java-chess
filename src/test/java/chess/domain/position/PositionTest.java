package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("위치값을 받아 포지션을 생성한다.")
    void create() {
        assertThat(Position.create("a1")).isEqualTo(new Position(File.A, Rank.ONE));
    }

    @Test
    @DisplayName("동쪽으로 이동한다.")
    void moveEast() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.EAST)).isEqualTo(new Position(File.E, Rank.FOUR));
    }

    @Test
    @DisplayName("서쪽으로 이동한다.")
    void moveWest() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.WEST)).isEqualTo(new Position(File.C, Rank.FOUR));
    }

    @Test
    @DisplayName("북쪽으로 이동한다.")
    void moveNorth() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.NORTH)).isEqualTo(new Position(File.D, Rank.FIVE));
    }

    @Test
    @DisplayName("남쪽으로 이동한다.")
    void moveSouth() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.SOUTH)).isEqualTo(new Position(File.D, Rank.THREE));
    }

    @Test
    @DisplayName("북동쪽으로 이동한다.")
    void moveNorthEast() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.NORTH_EAST)).isEqualTo(new Position(File.E, Rank.FIVE));
    }

    @Test
    @DisplayName("북서쪽으로 이동한다.")
    void moveNorthWest() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.NORTH_WEST)).isEqualTo(new Position(File.C, Rank.FIVE));
    }

    @Test
    @DisplayName("남동쪽으로 이동한다.")
    void moveSouthEast() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.SOUTH_EAST)).isEqualTo(new Position(File.E, Rank.THREE));
    }

    @Test
    @DisplayName("남서쪽으로 이동한다.")
    void moveSouthWest() {
        Position position = Position.create("d4");
        assertThat(position.move(Direction.SOUTH_WEST)).isEqualTo(new Position(File.C, Rank.THREE));
    }
}
