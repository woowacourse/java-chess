package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class KnightTest {
	@Test
	void Knight_PieceColor_GenerateInstance() {
		assertThat(new Knight(PieceColor.BLACK)).isInstanceOf(Knight.class);
	}

	@ParameterizedTest
	@NullSource
	void Knight_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new Knight(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("나이트의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Knight(PieceColor.BLACK).getName()).isEqualTo("N");
		assertThat(new Knight(PieceColor.WHITE).getName()).isEqualTo("n");
	}
}