package chess.service.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class PieceColorDtoTest {

	@ParameterizedTest
	@NullSource
	void of_NullPieceColor_ExceptionThrown(final PieceColor pieceColor) {
		assertThatThrownBy(() -> PieceColorDto.of(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 null입니다.");
	}

	@Test
	void of_PieceColor_GenerateInstance() {
		assertThat(PieceColorDto.of(PieceColor.BLACK)).isInstanceOf(PieceColorDto.class)
			.extracting("pieceColor").isEqualTo("black");
	}

}