package chess.domain.movement.continuous;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SouthWestMovementTest {

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.A, Rank.TWO);
        SouthWestMovement southWestMovement = new SouthWestMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(southWestMovement.isMovable(start, end, isEnemyExistAtEnd)).isTrue();
    }

    @Test
    @DisplayName("이동 불가능한지 확인한다.")
    void isMovableTest_false() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.B, Rank.TWO);
        SouthWestMovement southWestMovement = new SouthWestMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(southWestMovement.isMovable(start, end, isEnemyExistAtEnd)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.C, Rank.FOUR);
        Position end = new Position(File.A, Rank.TWO);
        SouthWestMovement southWestMovement = new SouthWestMovement();
        boolean isEnemyExistAtEnd = false;

        assertThat(southWestMovement.findPath(start, end, isEnemyExistAtEnd))
                .containsExactly(new Position(File.B, Rank.THREE), new Position(File.A, Rank.TWO));
    }
}
