package chess.domain.movement.discrete;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightMovementTest {

    @ParameterizedTest
    @CsvSource({"B, FIVE", "C, SIX", "E, SIX", "F, FIVE", "B, THREE", "C, TWO", "E, TWO", "F, THREE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        KnightMovement knightMovement = new KnightMovement();
        boolean isAttack = false;

        assertThat(knightMovement.isMovable(start, end, isAttack)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"C, FIVE", "B, SIX", "F, SIX", "E, FIVE", "C, THREE", "B, TWO", "F, TWO", "E, THREE"})
    @DisplayName("이동 가능한지 확인한다.")
    void isMovableTest_false(File file, Rank rank) {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(file, rank);
        KnightMovement knightMovement = new KnightMovement();
        boolean isAttack = false;

        assertThat(knightMovement.isMovable(start, end, isAttack)).isFalse();
    }

    @Test
    @DisplayName("이동 경로를 알 수 있다.")
    void findPathTest() {
        Position start = new Position(File.D, Rank.FOUR);
        Position end = new Position(File.B, Rank.FIVE);
        KnightMovement knightMovement = new KnightMovement();
        boolean isAttack = false;

        assertThat(knightMovement.findPath(start, end, isAttack))
                .containsExactly(end);
    }
}
