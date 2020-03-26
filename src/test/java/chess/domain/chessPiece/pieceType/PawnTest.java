package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;

class PawnTest {
	@Test
	void Pawn_PieceColor_GenerateInstance() {
		assertThat(new Pawn(PieceColor.BLACK, new WhitePawnRuleStrategy())).isInstanceOf(Pawn.class);
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Pawn(PieceColor.BLACK, new WhitePawnRuleStrategy()).getName()).isEqualTo("P");
		assertThat(new Pawn(PieceColor.WHITE, new WhitePawnRuleStrategy()).getName()).isEqualTo("p");
	}
}