package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.nonLeapableStrategy.RookRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class RookTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void Rook_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState rookInitialState = new InitialState(new RookRuleStrategy());

		assertThat(new Rook(pieceColor, rookInitialState)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,R", "WHITE,r"})
	void getName_PieceColor_ReturnName(PieceColor pieceColor, String expected) {
		PieceState rookInitialState = new InitialState(new RookRuleStrategy());

		assertThat(new Rook(pieceColor, rookInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_RookScore_ReturnScore(PieceColor pieceColor) {
		PieceState rookInitialState = new InitialState(new RookRuleStrategy());

		assertThat(new Rook(pieceColor, rookInitialState).getScore()).isEqualTo(5);
	}

}