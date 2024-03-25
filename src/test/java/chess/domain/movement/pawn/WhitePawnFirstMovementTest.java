package chess.domain.movement.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnFirstMovementTest {

    private static final boolean NOT_EXIST_ENEMY = false;
    private static final Rank AVAILABLE_START_RANK = Rank.TWO;

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.D, AVAILABLE_START_RANK);
        Position end = new Position(File.D, Rank.FOUR);
        WhitePawnFirstMovement whitePawnFirstMovement = new WhitePawnFirstMovement();

        assertThat(whitePawnFirstMovement.isMovable(start, end, NOT_EXIST_ENEMY)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"C, SEVEN", "D, ONE", "D, THREE", "D, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, AVAILABLE_START_RANK);
        Position end = new Position(file, rank);
        WhitePawnFirstMovement whitePawnFirstMovement = new WhitePawnFirstMovement();

        assertThat(whitePawnFirstMovement.isMovable(start, end, NOT_EXIST_ENEMY)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"C, ONE", "D, THREE", "D, FOUR"})
    @DisplayName("초기 위치가 아닐 경우, 이동 불가능하다.")
    void isMovableTest_notMatchInitialPosition(File file, Rank rank) {
        Position start = new Position(file, rank);
        Position end = start.moveToNorth().moveToNorth();
        WhitePawnFirstMovement whitePawnFirstMovement = new WhitePawnFirstMovement();

        assertThat(whitePawnFirstMovement.isMovable(start, end, NOT_EXIST_ENEMY)).isFalse();
    }

    @Test
    @DisplayName("도착 위치에 적이 있을 경우, 이동 불가능하다.")
    void isMovableTest_existEnemy() {
        Position start = new Position(File.B, AVAILABLE_START_RANK);
        Position end = start.moveToNorth().moveToNorth();
        boolean hasEnemy = true;
        WhitePawnFirstMovement whitePawnFirstMovement = new WhitePawnFirstMovement();

        assertThat(whitePawnFirstMovement.isMovable(start, end, hasEnemy)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.TWO);
        Position middle = new Position(File.D, Rank.THREE);
        Position end = new Position(File.D, Rank.FOUR);
        WhitePawnFirstMovement whitePawnFirstMovement = new WhitePawnFirstMovement();

        assertThat(whitePawnFirstMovement.findPath(start, end, NOT_EXIST_ENEMY))
                .containsExactly(middle);
    }
}
