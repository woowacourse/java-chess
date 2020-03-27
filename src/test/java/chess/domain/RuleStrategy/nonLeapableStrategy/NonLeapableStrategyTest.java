package chess.domain.RuleStrategy.nonLeapableStrategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.position.Position;

class NonLeapableStrategyTest {

	@ParameterizedTest
	@NullSource
	void validate_NullSource_ExceptionThrown(Position sourcePosition) {
		NonLeapableStrategy nonLeapableStrategy = new QueenRuleStrategy();
		Position targetPosition = Position.of("b1");

		assertThatThrownBy(() -> nonLeapableStrategy.canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 존재하지 않습니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTarget_ExceptionThrown(Position targetPosition) {
		NonLeapableStrategy nonLeapableStrategy = new QueenRuleStrategy();
		Position sourcePosition = Position.of("b1");

		assertThatThrownBy(() -> nonLeapableStrategy.canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 존재하지 않습니다.");
	}

	@Test
	void canLeap_ReturnFalse() {
		assertThat(new QueenRuleStrategy().canLeap()).isFalse();
	}

}