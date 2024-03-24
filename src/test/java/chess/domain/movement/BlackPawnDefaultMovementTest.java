package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackPawnDefaultMovementTest {

    @Test
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.D, Rank.THREE);
        BlackPawnDefaultMovement blackPawnDefaultMovement = new BlackPawnDefaultMovement();
        boolean isAttack = false;

        assertThat(blackPawnDefaultMovement.isMovable(start, end, isAttack)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"B, SIX", "F, SIX", "D, TWO", "D, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        BlackPawnDefaultMovement blackPawnDefaultMovement = new BlackPawnDefaultMovement();
        boolean isAttack = false;

        assertThat(blackPawnDefaultMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("상대 말이 이동하고자 하는 곳에 있을 때, 이동이 불가능하다.")
    void isMovableTest_whenisAttack_false() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.D, Rank.THREE);
        BlackPawnDefaultMovement blackPawnDefaultMovement = new BlackPawnDefaultMovement();
        boolean isAttack = true;

        assertThat(blackPawnDefaultMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.D, Rank.THREE);
        BlackPawnDefaultMovement blackPawnDefaultMovement = new BlackPawnDefaultMovement();
        boolean isAttack = false;

        assertThat(blackPawnDefaultMovement.findPath(start, end, isAttack))
                .containsExactly(end);
    }
}
