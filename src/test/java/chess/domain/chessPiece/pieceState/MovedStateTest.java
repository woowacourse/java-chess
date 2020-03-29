package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

class MovedStateTest {

	@Test
	void MovedState_RuleStrategy_GenerateInstance() {
		assertThat(new MovedState(new KingRuleStrategy())).isInstanceOf(MovedState.class);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void shiftNextState_PieceColor_ReturnNextState(PieceColor pieceColor) {
		MovedState movedState = new MovedState(new KingRuleStrategy());

		assertThat(movedState.shiftNextState(pieceColor)).isInstanceOf(MovedState.class);
	}

	@ParameterizedTest
	@NullSource
	void shiftNextState_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		MovedState movedState = new MovedState(new KingRuleStrategy());

		assertThatThrownBy(() -> movedState.shiftNextState(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 존재하지 않습니다.");
	}

}