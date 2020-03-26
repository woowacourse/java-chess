package chess.domain.RuleStrategy.nonLeapableStrategy;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopRuleStrategyTest {
    @Test
    void BishopMovableStrategy_GenerateInstance() {
        assertThat(new BishopRuleStrategy()).isInstanceOf(BishopRuleStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        RuleStrategy ruleStrategy = new BishopRuleStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("c4");

        assertThat(ruleStrategy.canMove(source, target)).isTrue();
    }
}