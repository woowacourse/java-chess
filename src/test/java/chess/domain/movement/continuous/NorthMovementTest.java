package chess.domain.movement.continuous;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NorthMovementTest {

    private static final boolean HAS_ENEMY = false;

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.C, Rank.SIX);
        NorthMovement northMovement = new NorthMovement();

        assertThat(northMovement.isMovable(start, end, HAS_ENEMY)).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한지 확인한다.")
    void isMovableTest_false() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.B, Rank.SIX);
        NorthMovement northMovement = new NorthMovement();

        assertThat(northMovement.isMovable(start, end, HAS_ENEMY)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.C, Rank.SEVEN);
        NorthMovement northMovement = new NorthMovement();

        assertThat(northMovement.findPath(start, end, HAS_ENEMY))
                .containsExactly(new Position(File.C, Rank.FIVE), new Position(File.C, Rank.SIX));
    }
}
