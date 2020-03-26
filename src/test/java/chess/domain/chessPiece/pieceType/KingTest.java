package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.RuleStrategy.KingRuleStrategy;

class KingTest {
	@Test
	void King_PieceColor_GenerateInstance() {
		assertThat(new King(PieceColor.BLACK, new KingRuleStrategy())).isInstanceOf(King.class);
	}

	@Test
	void getName_ReturnName() {
		assertThat(new King(PieceColor.BLACK, new KingRuleStrategy()).getName()).isEqualTo("K");
		assertThat(new King(PieceColor.WHITE, new KingRuleStrategy()).getName()).isEqualTo("k");
	}
}