package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WhitePawnRuleStrategyTest {

    private static final int MOVABLE_RANGE = 1;

    @Test
    void WhitePawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
        assertThat(new WhitePawnRuleStrategy(MOVABLE_RANGE)).isInstanceOf(WhitePawnRuleStrategy.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,c4", "2,c5"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(int movableRange, Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy(movableRange).canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4", "d4"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy(MOVABLE_RANGE).canMove(sourcePosition, targetPosition)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4", "d4"})
    void canMoveToCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy(MOVABLE_RANGE).canMoveToCatch(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c4", "c5"})
    void canMoveToCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy(MOVABLE_RANGE).canMoveToCatch(sourcePosition, targetPosition)).isFalse();
    }

}