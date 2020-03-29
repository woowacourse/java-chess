package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class QueenTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Queen_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState queenInitialState = new InitialState(new QueenRuleStrategy());

		assertThat(new Queen(pieceColor, queenInitialState)).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,Q", "WHITE,q"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		PieceState queenInitialState = new InitialState(new QueenRuleStrategy());

		assertThat(new Queen(pieceColor, queenInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_QueenScore_ReturnScore(PieceColor pieceColor) {
		PieceState queenInitialState = new InitialState(new QueenRuleStrategy());

		assertThat(new Queen(pieceColor, queenInitialState).getScore()).isEqualTo(9);
	}

}