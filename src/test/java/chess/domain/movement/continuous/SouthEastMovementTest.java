package chess.domain.movement.continuous;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SouthEastMovementTest {

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.E, Rank.TWO);
        SouthEastMovement southEastMovement = new SouthEastMovement();

        assertThat(southEastMovement.isMovable(start, end)).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한지 확인한다.")
    void isMovableTest_false() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.D, Rank.TWO);
        SouthEastMovement southEastMovement = new SouthEastMovement();

        assertThat(southEastMovement.isMovable(start, end)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.E, Rank.TWO);
        SouthEastMovement southEastMovement = new SouthEastMovement();

        assertThat(southEastMovement.findPath(start, end))
                .containsExactly(new Position(File.D, Rank.THREE), new Position(File.E, Rank.TWO));
    }
}
