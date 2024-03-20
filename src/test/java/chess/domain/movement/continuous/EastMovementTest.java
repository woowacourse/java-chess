package chess.domain.movement.continuous;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EastMovementTest {

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.E, Rank.FOUR);
        EastMovement eastMovement = new EastMovement();

        assertThat(eastMovement.isMovable(start, end)).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한지 확인한다.")
    void isMovableTest_false() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.E, Rank.THREE);
        EastMovement eastMovement = new EastMovement();

        assertThat(eastMovement.isMovable(start, end)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.E, Rank.FOUR);
        EastMovement eastMovement = new EastMovement();

        assertThat(eastMovement.findPath(start, end))
                .containsExactly(new Position(File.D, Rank.FOUR), new Position(File.E, Rank.FOUR));
    }
}
