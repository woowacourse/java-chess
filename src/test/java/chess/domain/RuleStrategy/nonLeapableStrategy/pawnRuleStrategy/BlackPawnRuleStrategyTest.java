package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class BlackPawnRuleStrategyTest {

	@Test
	void BlackPawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
		assertThat(new BlackPawnRuleStrategy()).isInstanceOf(BlackPawnRuleStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"c2"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy().canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy().canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canMoveToCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy().canMoveToCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c2", "c1"})
	void canMoveToCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy().canMoveToCatch(sourcePosition, targetPosition)).isFalse();
	}

}