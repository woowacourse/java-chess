package chess.domain.chessPiece.pieceType;

import static chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class PawnTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Pawn_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState pawnInitialState = new InitialState(new BlackPawnRuleStrategy(MOVED_STATE_MOVABLE_RANGE));

		assertThat(new Pawn(pieceColor, pawnInitialState)).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,P", "WHITE,p"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		PieceState pawnInitialState = new InitialState(new BlackPawnRuleStrategy(MOVED_STATE_MOVABLE_RANGE));

		assertThat(new Pawn(pieceColor, pawnInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_PawnScore_ReturnScore(PieceColor pieceColor) {
		PieceState pawnInitialState = new InitialState(new BlackPawnRuleStrategy(MOVED_STATE_MOVABLE_RANGE));

		assertThat(new Pawn(pieceColor, pawnInitialState).getScore()).isEqualTo(1);
	}

}