package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WhitePawnRuleStrategyTest {

    @Test
    void WhitePawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
        assertThat(new WhitePawnRuleStrategy()).isInstanceOf(WhitePawnRuleStrategy.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,c4", "2,c5"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy().canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4", "d4"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy().canMove(sourcePosition, targetPosition)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b4", "d4"})
    void canMoveToCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy().canMoveToCatch(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c4", "c5"})
    void canMoveToCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new WhitePawnRuleStrategy().canMoveToCatch(sourcePosition, targetPosition)).isFalse();
    }

}