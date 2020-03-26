package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;

class QueenTest {
	@Test
	void Queen_PieceColor_GenerateInstance() {
		assertThat(new Queen(PieceColor.BLACK, new QueenRuleStrategy())).isInstanceOf(Queen.class);
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Queen(PieceColor.BLACK, new QueenRuleStrategy()).getName()).isEqualTo("Q");
		assertThat(new Queen(PieceColor.WHITE, new QueenRuleStrategy()).getName()).isEqualTo("q");
	}
}