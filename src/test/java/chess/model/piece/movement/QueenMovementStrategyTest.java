package chess.model.piece.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.helper.CommonAttackEvaluator;
import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenMovementStrategyTest extends CommonAttackEvaluator {

    private final MovementStrategy movement = QueenMovementStrategy.MOVEMENT;
    
    @ParameterizedTest(name = "목적지가 적군이거나 빈 곳인 경우 움직이는 방향이 ({0} / {1})일 때 움직일 수 있다.")
    @DisplayName("movable() 유효한 이동 방향, 유효한 이동 거리, 적군 테스트")
    @CsvSource(value = {"1:1", "1:-1", "-1:-1", "-1:1", "0:1", "0:-1", "1:0", "-1:0"}, delimiter = ':')
    void movable_givenValidDistanceAndEnemyTarget_thenReturnTrue(final int file, final int rank) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean actual = movement.movable(distance, blackEnemyEvaluator);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목적지가 아군인 경우 거리와 무관하게 움직일 수 없다.")
    void movable_givenValidDistanceAndAllyTarget_thenReturnFalse() {
        // given
        final Distance distance = new Distance(1, 1);

        // when
        final boolean actual = movement.movable(distance, blackAllyEvaluator);

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest(name = "움직이는 방향 ({0} / {1})과 같이 유효하지 않은 경우 목적지와 무관하게 움직일 수 없다.")
    @DisplayName("movable() 유효하지 않은 이동 방향 테스트")
    @CsvSource(value = {
            "2:1", "2:-1", "-2:1", "-2:-1", "1:2", "1:-2", "-1:2", "-1:-2"
    }, delimiter = ':')
    void movable_giveInvalidDistance_thenReturnFalse(final int invalidFile, final int invalidRank) {
        // given
        final Distance invalidDistance = new Distance(invalidFile, invalidRank);

        // when
        final boolean actual = movement.movable(invalidDistance, blackEmptyEvaluator);

        // then
        assertThat(actual).isFalse();
    }
}
