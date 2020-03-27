package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class BlackPawnRuleStrategyTest {

	private static final int MOVABLE_RANGE = 1;

	@Test
	void BlackPawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
		assertThat(new BlackPawnRuleStrategy(MOVABLE_RANGE)).isInstanceOf(BlackPawnRuleStrategy.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,c2", "2,c1"})
	void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(int movableRange, Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy(movableRange).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy(MOVABLE_RANGE).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2", "d2"})
	void canMoveToCatch_CatchableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy(MOVABLE_RANGE).canMoveToCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"c2", "c1"})
	void canMoveToCatch_NonCatchableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
		Position sourcePosition = Position.of("c3");

		assertThat(new BlackPawnRuleStrategy(MOVABLE_RANGE).canMoveToCatch(sourcePosition, targetPosition)).isFalse();
	}

}