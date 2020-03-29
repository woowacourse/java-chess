package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.KnightRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class KnightTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Knight_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState knightInitialState = new InitialState(new KnightRuleStrategy());

		assertThat(new Knight(pieceColor, knightInitialState)).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,N", "WHITE,n"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		PieceState knightInitialState = new InitialState(new KnightRuleStrategy());

		assertThat(new Knight(pieceColor, knightInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_KnightScore_ReturnScore(PieceColor pieceColor) {
		PieceState knightInitialState = new InitialState(new KnightRuleStrategy());

		assertThat(new Knight(pieceColor, knightInitialState).getScore()).isEqualTo(2.5);
	}

}