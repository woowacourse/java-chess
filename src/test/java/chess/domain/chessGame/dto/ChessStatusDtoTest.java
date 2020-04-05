package chess.domain.chessGame.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class ChessStatusDtoTest {

	@Test
	void of_PieceColorAndScore_GenerateInstance() {
		assertThat(ChessStatusDto.of(PieceColor.BLACK, 20.)).isInstanceOf(ChessStatusDto.class);
	}

	@ParameterizedTest
	@NullSource
	void of_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> ChessStatusDto.of(pieceColor, 20.))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("피스 색상이 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void of_NullScore_ExceptionThrown(Double score) {
		assertThatThrownBy(() -> ChessStatusDto.of(PieceColor.BLACK, score))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("점수가 null입니다.");
	}

}