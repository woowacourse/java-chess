package chess.domain.RuleStrategy.nonLeapableStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class QueenRuleStrategyTest {

	@Test
	void QueenRuleStrategy_MovableDirection_GenerateInstance() {
		assertThat(new QueenRuleStrategy()).isInstanceOf(QueenRuleStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"a1", "d1", "d8", "a7", "h8", "g1", "a4", "h4"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		QueenRuleStrategy queenRuleStrategy = new QueenRuleStrategy();
		Position sourcePosition = Position.of("d4");

		assertThat(queenRuleStrategy.canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b3", "c2", "f3", "e6"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		QueenRuleStrategy queenRuleStrategy = new QueenRuleStrategy();
		Position sourcePosition = Position.of("d4");

		assertThat(queenRuleStrategy.canMove(sourcePosition, targetPosition)).isFalse();
	}

}