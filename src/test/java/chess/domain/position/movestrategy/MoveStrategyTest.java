package chess.domain.position.movestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveStrategyTest {

    @Test
    @DisplayName("동쪽으로 이동한다.")
    void moveEast() {
        MoveStrategy moveStrategy = new East();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.FOUR));
    }

    @Test
    @DisplayName("서쪽으로 이동한다.")
    void moveWest() {
        MoveStrategy moveStrategy = new West();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.FOUR));
    }

    @Test
    @DisplayName("북쪽으로 이동한다.")
    void moveNorth() {
        MoveStrategy moveStrategy = new North();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.D, Rank.FIVE));
    }

    @Test
    @DisplayName("남쪽으로 이동한다.")
    void moveSouth() {
        MoveStrategy moveStrategy = new South();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.D, Rank.THREE));
    }

    @Test
    @DisplayName("북동쪽으로 이동한다.")
    void moveNorthEast() {
        MoveStrategy moveStrategy = new NorthEast();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.FIVE));
    }

    @Test
    @DisplayName("북서쪽으로 이동한다.")
    void moveNorthWest() {
        MoveStrategy moveStrategy = new NorthWest();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.FIVE));
    }

    @Test
    @DisplayName("남동쪽으로 이동한다.")
    void moveSouthEast() {
        MoveStrategy moveStrategy = new SouthEast();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.E, Rank.THREE));
    }

    @Test
    @DisplayName("남서쪽으로 이동한다.")
    void moveSouthWest() {
        MoveStrategy moveStrategy = new SouthWest();
        assertThat(moveStrategy.move(File.D, Rank.FOUR)).isEqualTo(new Position(File.C, Rank.THREE));
    }
}