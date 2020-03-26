package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class RookTest {
	@Test
	void Rook_PieceColor_GenerateInstance() {
		assertThat(new Rook(PieceColor.BLACK)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@NullSource
	void Rook_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new Rook(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("룩의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Rook(PieceColor.BLACK).getName()).isEqualTo("R");
		assertThat(new Rook(PieceColor.WHITE).getName()).isEqualTo("r");
	}
}