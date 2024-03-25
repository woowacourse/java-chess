package chess.domain.movement.pawn;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackPawnDiagonalMovementTest {

    private static final boolean EXIST_ENEMY = true;

    @ParameterizedTest
    @CsvSource({"C, THREE", "E, THREE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        BlackPawnDiagonalMovement blackPawnDiagonalMovement = new BlackPawnDiagonalMovement();

        assertThat(blackPawnDiagonalMovement.isMovable(start, end, EXIST_ENEMY)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"B, SIX", "F, SIX", "D, TWO", "D, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        BlackPawnDiagonalMovement blackPawnDiagonalMovement = new BlackPawnDiagonalMovement();

        assertThat(blackPawnDiagonalMovement.isMovable(start, end, EXIST_ENEMY)).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"C, THREE", "E, THREE"})
    @DisplayName("도착 위치에 적이 없을 경우, 이동 불가능하다.")
    void isMovableTest_notExistEnemy(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        boolean hasEnemy = false;
        BlackPawnDiagonalMovement blackPawnDiagonalMovement = new BlackPawnDiagonalMovement();

        assertThat(blackPawnDiagonalMovement.isMovable(start, end, hasEnemy)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.E, Rank.THREE);
        BlackPawnDiagonalMovement blackPawnDiagonalMovement = new BlackPawnDiagonalMovement();

        assertThat(blackPawnDiagonalMovement.findPath(start, end, EXIST_ENEMY))
                .containsExactly(end);
    }
}
