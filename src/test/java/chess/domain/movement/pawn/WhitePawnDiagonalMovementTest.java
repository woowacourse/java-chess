package chess.domain.movement.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnDiagonalMovementTest {

    private static final boolean EXIST_ENEMY = true;

    @ParameterizedTest
    @CsvSource({"C, FIVE", "E, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, EXIST_ENEMY)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"B, SIX", "F, SIX", "D, THREE", "D, SIX"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, EXIST_ENEMY)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"C, FIVE", "E, FIVE"})
    @DisplayName("도착 위치에 적이 없을 경우, 이동 불가능하다.")
    void isMovableTest_notExistEnemy(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean hasEnemy = false;
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, hasEnemy)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.D, Rank.FIVE);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();

        assertThat(whitePawnDiagonalMovement.findPath(start, end, EXIST_ENEMY))
                .containsExactly(end);
    }
}
