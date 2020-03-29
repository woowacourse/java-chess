package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;

class KingTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void King_PieceColorAndPieceState_GenerateInstance(PieceColor pieceColor) {
		PieceState kingInitialState = new InitialState(new KingRuleStrategy());

		assertThat(new King(pieceColor, kingInitialState)).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,K", "WHITE,k"})
	void getName_ReturnName(PieceColor pieceColor, String expected) {
		PieceState kingInitialState = new InitialState(new KingRuleStrategy());

		assertThat(new King(pieceColor, kingInitialState).getName()).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void getScore_KingScore_ReturnScore(PieceColor pieceColor) {
		PieceState kingInitialState = new InitialState(new KingRuleStrategy());

		assertThat(new King(pieceColor, kingInitialState).getScore()).isEqualTo(0);
	}

}