package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	@CsvSource(value = {"BLACK,true", "WHITE,false"})
	void isBlack_ReturnCompareResult(PieceColor pieceColor, boolean expected) {
		assertThat(pieceColor.isBlack()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"BLACK,false", "WHITE,true"})
	void isWhite_ReturnCompareResult(PieceColor pieceColor, boolean expected) {
		assertThat(pieceColor.isWhite()).isEqualTo(expected);
	}

}

