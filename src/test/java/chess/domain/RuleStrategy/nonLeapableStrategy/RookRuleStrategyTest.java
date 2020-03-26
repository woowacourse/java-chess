package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookRuleStrategyTest {
    @Test
    void RookMovableStrategy_GenerateInstance() {
        assertThat(new RookRuleStrategy()).isInstanceOf(RookRuleStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        RuleStrategy ruleStrategy = new RookRuleStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("b8");

        assertThat(ruleStrategy.canMove(source, target)).isTrue();
    }
}