package chess.domain.movement.continuous;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NorthWestMovementTest {

    private static final boolean HAS_ENEMY = false;

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.A, Rank.SIX);
        NorthWestMovement northWestMovement = new NorthWestMovement();

        assertThat(northWestMovement.isMovable(start, end, HAS_ENEMY)).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한지 확인한다.")
    void isMovableTest_false() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.B, Rank.SIX);
        NorthWestMovement northWestMovement = new NorthWestMovement();

        assertThat(northWestMovement.isMovable(start, end, HAS_ENEMY)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.A, Rank.SEVEN);
        NorthWestMovement northWestMovement = new NorthWestMovement();

        assertThat(northWestMovement.findPath(start, end, HAS_ENEMY))
                .containsExactly(new Position(File.C, Rank.FIVE), new Position(File.B, Rank.SIX));
    }
}
