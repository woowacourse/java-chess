package chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.position.Position;

class PawnRuleStrategyTest {

	@Test
	void PawnRuleStrategy_MovableAndCatchableDirections_GenerateInstance() {
		PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();

		assertThat(pawnRuleStrategy).isInstanceOf(PawnRuleStrategy.class);
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();
		Position targetPosition = Position.of("b1");

		assertThatThrownBy(() -> pawnRuleStrategy.canMoveToCatch(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		PawnRuleStrategy pawnRuleStrategy = new BlackPawnRuleStrategy();
		Position sourcePosition = Position.of("b1");

		assertThatThrownBy(() -> pawnRuleStrategy.canMoveToCatch(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 존재하지 않습니다.");
	}

}