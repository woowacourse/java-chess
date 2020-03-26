package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenRuleStrategyTest {
    @Test
    void QueenMovableStrategy_GenerateInstance() {
        assertThat(new QueenRuleStrategy()).isInstanceOf(QueenRuleStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        RuleStrategy ruleStrategy = new QueenRuleStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("e6");

        assertThat(ruleStrategy.canMove(source, target)).isTrue();
    }
}