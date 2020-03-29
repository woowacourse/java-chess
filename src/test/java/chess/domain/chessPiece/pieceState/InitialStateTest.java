package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

class InitialStateTest {

	@Test
	void InitialState_RuleStrategy_GenerateInstance() {
		assertThat(new InitialState(new KingRuleStrategy())).isInstanceOf(InitialState.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftNextState_PieceColor_ReturnNextState(PieceColor pieceColor) {
		InitialState initialState = new InitialState(new KingRuleStrategy());

		assertThat(initialState.shiftNextState(pieceColor)).isInstanceOf(MovedState.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftNextState_PieceColor_ReturnNextStateByPawnRuleStrategy(PieceColor pieceColor) {
		InitialState initialState = new InitialState(
			pieceColor.getPawnRuleStrategyBy(PawnRuleStrategy.INITIAL_STATE_MOVABLE_RANGE));

		assertThat(initialState.shiftNextState(pieceColor)).isInstanceOf(MovedState.class)
			.extracting("ruleStrategy").isInstanceOf(PawnRuleStrategy.class)
			.extracting("movableRange").isEqualTo(PawnRuleStrategy.MOVED_STATE_MOVABLE_RANGE);
	}

	@ParameterizedTest
	@NullSource
	void shiftNextState_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		InitialState initialState = new InitialState(new KingRuleStrategy());

		assertThatThrownBy(() -> initialState.shiftNextState(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 존재하지 않습니다.");
	}

}