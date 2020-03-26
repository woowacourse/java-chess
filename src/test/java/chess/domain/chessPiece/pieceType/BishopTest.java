package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class BishopTest {
	@Test
	void Bishop_PieceColor_GenerateInstance() {
		assertThat(new Bishop(PieceColor.BLACK)).isInstanceOf(Bishop.class);
	}

	@ParameterizedTest
	@NullSource
	void Bishop_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new Bishop(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("비숍의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Bishop(PieceColor.BLACK).getName()).isEqualTo("B");
		assertThat(new Bishop(PieceColor.WHITE).getName()).isEqualTo("b");
	}
}