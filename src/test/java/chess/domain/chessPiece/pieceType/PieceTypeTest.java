package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.RuleStrategy.RuleStrategy;

class PieceTypeTest {
	@Test
	void PieceType_PieceColorAndMovableStrategy_GenerateInstance() {
		PieceType pieceType = new King(PieceColor.BLACK, new KingRuleStrategy());

		assertThat(pieceType).isInstanceOf(PieceType.class);
	}

	@ParameterizedTest
	@NullSource
	void PieceType_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new King(pieceColor, new KingRuleStrategy()))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void PieceType_NullMovableStrategy_ExceptionThrown(RuleStrategy ruleStrategy) {
		assertThatThrownBy(() -> new King(PieceColor.BLACK, ruleStrategy))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 전략이 null입니다.");
	}
}