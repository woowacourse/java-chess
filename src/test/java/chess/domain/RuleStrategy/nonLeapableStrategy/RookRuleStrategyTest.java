package chess.domain.RuleStrategy.nonLeapableStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class RookRuleStrategyTest {

	@Test
	void RookRuleStrategy_MovableDirection_GenerateInstance() {
		assertThat(new RookRuleStrategy()).isInstanceOf(RookRuleStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"d1", "d8", "a4", "h4"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		RookRuleStrategy rookRuleStrategy = new RookRuleStrategy();
		Position sourcePosition = Position.of("d4");

		assertThat(rookRuleStrategy.canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "a7", "g1", "g7"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		RookRuleStrategy rookRuleStrategy = new RookRuleStrategy();
		Position sourcePosition = Position.of("d4");

		assertThat(rookRuleStrategy.canMove(sourcePosition, targetPosition)).isFalse();
	}

}