package chess.domain.position.diraction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("동쪽으로 이동한다.")
    void moveEast() {
        Direction direction = new East();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.FOUR));
    }

    @Test
    @DisplayName("서쪽으로 이동한다.")
    void moveWest() {
        Direction direction = new West();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.FOUR));
    }

    @Test
    @DisplayName("북쪽으로 이동한다.")
    void moveNorth() {
        Direction direction = new North();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.D, Rank.FIVE));
    }

    @Test
    @DisplayName("남쪽으로 이동한다.")
    void moveSouth() {
        Direction direction = new South();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.D, Rank.THREE));
    }

    @Test
    @DisplayName("북동쪽으로 이동한다.")
    void moveNorthEast() {
        Direction direction = new NorthEast();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.FIVE));
    }

    @Test
    @DisplayName("북서쪽으로 이동한다.")
    void moveNorthWest() {
        Direction direction = new NorthWest();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.FIVE));
    }

    @Test
    @DisplayName("남동쪽으로 이동한다.")
    void moveSouthEast() {
        Direction direction = new SouthEast();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.THREE));
    }

    @Test
    @DisplayName("남서쪽으로 이동한다.")
    void moveSouthWest() {
        Direction direction = new SouthWest();
        assertThat(direction.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.THREE));
    }
}