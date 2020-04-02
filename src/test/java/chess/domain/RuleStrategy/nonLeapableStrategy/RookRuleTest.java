package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookRuleTest {

    @Test
    void RookRuleStrategy_MovableDirection_GenerateInstance() {
        assertThat(new RookRule()).isInstanceOf(RookRule.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"d1", "d8", "a4", "h4"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        RookRule rookRuleStrategy = new RookRule();
        Position sourcePosition = Position.of("d4");

        assertThat(rookRuleStrategy.canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a1", "a7", "g1", "g7"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        RookRule rookRuleStrategy = new RookRule();
        Position sourcePosition = Position.of("d4");

        assertThat(rookRuleStrategy.canMove(sourcePosition, targetPosition)).isFalse();
    }

}