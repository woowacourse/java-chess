package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class BishopTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Bishop_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState bishopInitialState = new InitialState(new BishopRuleStrategy());

		assertThat(new Bishop(pieceColor, bishopInitialState)).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,B", "WHITE,b"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		PieceState bishopInitialState = new InitialState(new BishopRuleStrategy());

		assertThat(new Bishop(pieceColor, bishopInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_BishopScore_ReturnScore(PieceColor pieceColor) {
		PieceState bishopInitialState = new InitialState(new BishopRuleStrategy());

		assertThat(new Bishop(pieceColor, bishopInitialState).getScore()).isEqualTo(3);
	}

}