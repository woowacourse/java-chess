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

    @Test
    void canMove_InitialStateMovableSourcePositionAndTargetPosition_ReturnTrue() {
        Position sourcePosition = Position.of("c7");

        assertThat(new BlackPawnRule().canInitialMove(sourcePosition, Position.of("c5"))).isTrue();
    }

    @Test
    void canMove_MovedStateMovableSourcePositionAndTargetPosition_ReturnTrue() {
        Position sourcePosition = Position.of("c7");

        assertThat(new BlackPawnRule().canMove(sourcePosition, Position.of("c6"))).isTrue();
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