package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlackPawnRuleTest {

    @Test
    void BlackPawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
        assertThat(new BlackPawnRule()).isInstanceOf(BlackPawnRule.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"c2", "c1"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new BlackPawnRule().canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b2", "d2"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new BlackPawnRule().canMove(sourcePosition, targetPosition)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b2", "d2"})
    void canMoveToCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new BlackPawnRule().canMoveToCatch(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c2", "c1"})
    void canMoveToCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        Position sourcePosition = Position.of("c3");

        assertThat(new BlackPawnRule().canMoveToCatch(sourcePosition, targetPosition)).isFalse();
    }

}