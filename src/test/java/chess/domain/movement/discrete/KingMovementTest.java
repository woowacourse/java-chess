package chess.domain.movement.discrete;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingMovementTest {

    @ParameterizedTest
    @CsvSource({"D, FIVE", "E, FIVE", "E, FOUR", "E, THREE", "D, THREE", "C, THREE", "C, FOUR", "C, FIVE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        KingMovement kingMovement = new KingMovement();
        boolean isAttack = false;

        assertThat(kingMovement.isMovable(start, end, isAttack)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"B, SIX", "F, SIX", "B, TWO", "F, TWO"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        KingMovement kingMovement = new KingMovement();
        boolean isAttack = false;

        assertThat(kingMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.D, Rank.FIVE);
        KingMovement kingMovement = new KingMovement();
        boolean isAttack = false;

        assertThat(kingMovement.findPath(start, end, isAttack))
                .containsExactly(end);
    }
}
