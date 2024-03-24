package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnDiagonalMovementTest {

    @ParameterizedTest
    @CsvSource({"C, FOUR", "E, FOUR"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest(File file, Rank rank) {
        Position start = new Position(File.D, Rank.THREE);
        Position end = new Position(file, rank);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();
        boolean isAttack = true;

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, isAttack)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"D, FOUR", "D, FIVE", "C, THREE", "E, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.THREE);
        Position end = new Position(file, rank);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();
        boolean isAttack = true;

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("상대 말이 이동하고자 하는 곳에 없을 때, 이동이 불가능하다.")
    void isMovableTest_whenisAttack_false() {
        Position start = new Position(File.D, Rank.THREE);
        Position end = new Position(File.C, Rank.FOUR);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();
        boolean isAttack = false;

        assertThat(whitePawnDiagonalMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.THREE);
        Position end = new Position(File.C, Rank.FOUR);
        WhitePawnDiagonalMovement whitePawnDiagonalMovement = new WhitePawnDiagonalMovement();
        boolean isAttack = true;

        assertThat(whitePawnDiagonalMovement.findPath(start, end, isAttack))
                .containsExactly(end);
    }
}
