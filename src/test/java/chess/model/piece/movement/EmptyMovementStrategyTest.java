package chess.model.piece.movement;

import static org.assertj.core.api.Assertions.assertThat;

import chess.helper.CommonAttackEvaluator;
import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyMovementStrategyTest extends CommonAttackEvaluator {

    @Test
    @DisplayName("movable()은 호출하면 false를 반환한다.")
    void movable_whenCall_thenReturnFalse() {
        // given
        final EmptyMovementStrategy movementStrategy = EmptyMovementStrategy.MOVEMENT;
        final Distance ignoredDistance = new Distance(1, 1);

        // when
        final boolean actual = movementStrategy.movable(ignoredDistance, blackEmptyEvaluator);

        // then
        assertThat(actual).isFalse();
    }
}
