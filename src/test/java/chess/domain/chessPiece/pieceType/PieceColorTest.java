package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;

public class PieceColorTest {

	@ParameterizedTest
	@ValueSource(strings = {"white", "black"})
	void of_InputPieceColor_ReturnInstance(String color) {
		assertThat(PieceColor.of(color)).isInstanceOf(PieceColor.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,f,F", "WHITE,G,g"})
	void convertFrom_PieceName_ConvertedNameByPieceColor(PieceColor pieceColor, String pieceName, String expected) {
		assertThat(pieceColor.convertFrom(pieceName)).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void convertFrom_PieceName_ConvertedNameByPieceColor(String pieceName) {
		assertThatThrownBy(() -> PieceColor.WHITE.convertFrom(pieceName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("체스 이름이 존재하지 않습니다.");
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2})
	void getPawnRuleStrategyBy_MovableRange_ReturnPawnRuleStrategyByPieceColor(int movableRange) {
		assertThat(PieceColor.BLACK.getPawnRuleStrategyBy(movableRange)).isInstanceOf(BlackPawnRuleStrategy.class);
		assertThat(PieceColor.WHITE.getPawnRuleStrategyBy(movableRange)).isInstanceOf(WhitePawnRuleStrategy.class);
	}

}

