package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.RuleStrategy.KnightRuleStrategy;

class KnightTest {
	@Test
	void Knight_PieceColor_GenerateInstance() {
		assertThat(new Knight(PieceColor.BLACK, new KnightRuleStrategy())).isInstanceOf(Knight.class);
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Knight(PieceColor.BLACK, new KnightRuleStrategy()).getName()).isEqualTo("N");
		assertThat(new Knight(PieceColor.WHITE, new KnightRuleStrategy()).getName()).isEqualTo("n");
	}
}