package chess.domain.chessPiece.pieceType;

import static chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;
import chess.domain.chessPiece.pieceState.InitialState;

class PawnTest {
	@Test
	void Pawn_PieceColor_GenerateInstance() {
		assertThat(new Pawn(PieceColor.BLACK,
			new InitialState(new WhitePawnRuleStrategy(INITIAL_STATE_MOVABLE_RANGE)))).isInstanceOf(Pawn.class);
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Pawn(PieceColor.BLACK,
			new InitialState(new WhitePawnRuleStrategy(INITIAL_STATE_MOVABLE_RANGE))).getName()).isEqualTo("P");
		assertThat(new Pawn(PieceColor.WHITE,
			new InitialState(new WhitePawnRuleStrategy(INITIAL_STATE_MOVABLE_RANGE))).getName()).isEqualTo("p");
	}
}