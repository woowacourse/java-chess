package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.KnightRuleStrategy;
import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;
import chess.domain.position.Position;

class ChessMoveStateTest {

	@Test
	void ChessMoveState_RuleStrategy_GenerateInstance() {
		assertThat(new InitialState(new KingRuleStrategy())).isInstanceOf(ChessMoveState.class);
	}

	@ParameterizedTest
	@NullSource
	void ChessMoveState_NullRuleStrategy_ExceptionThrown(RuleStrategy ruleStrategy) {
		assertThatThrownBy(() -> new InitialState(ruleStrategy))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("체스 전략이 null입니다.");
	}

	@Test
	void canLeap_CanLeapRuleStrategy_ReturnTrue() {
		assertThat(new InitialState(new KnightRuleStrategy()).canLeap()).isTrue();
	}

	@Test
	void canLeap_CanNotLeapRuleStrategy_ReturnFalse() {
		assertThat(new InitialState(new QueenRuleStrategy()).canLeap()).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2,b4", "b2,a2"})
	void canMove_CanMoveSourcePositionAndTargetPosition_ReturnTrue(Position sourcePosition, Position targetPosition) {
		assertThat(new InitialState(new RookRuleStrategy()).canMove(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2,c4", "b2,c1"})
	void canMove_CanNotMoveSourcePositionAndTargetPosition_ReturnFalse(Position sourcePosition,
		Position targetPosition) {
		assertThat(new InitialState(new RookRuleStrategy()).canMove(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2,c3", "b2,a3"})
	void canCatch_CanCatchSourcePositionAndTargetPosition_ReturnTrueByPawnCanMoveToCatch(Position sourcePosition,
		Position targetPosition) {
		assertThat(new InitialState(new WhitePawnRuleStrategy(2)).canCatch(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"b2,b4", "b2,b3"})
	void canCatch_CanNotCatchSourcePositionAndTargetPosition_ReturnFalse(Position sourcePosition,
		Position targetPosition) {
		assertThat(new InitialState(new BishopRuleStrategy()).canCatch(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@NullSource
	void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
		Position targetPosition = Position.of("b1");

		assertThatThrownBy(() -> new InitialState(new RookRuleStrategy()).canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
		Position sourcePosition = Position.of("b1");

		assertThatThrownBy(() -> new InitialState(new RookRuleStrategy()).canMove(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

}