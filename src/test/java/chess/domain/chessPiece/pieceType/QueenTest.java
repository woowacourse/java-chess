package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class QueenTest {
	@Test
	void Queen_PieceColor_GenerateInstance() {
		assertThat(new Queen(PieceColor.BLACK)).isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@NullSource
	void Queen_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new Queen(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("퀸의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Queen(PieceColor.BLACK).getName()).isEqualTo("Q");
		assertThat(new Queen(PieceColor.WHITE).getName()).isEqualTo("q");
	}
}