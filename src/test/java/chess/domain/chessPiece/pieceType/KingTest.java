package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class KingTest {
	@Test
	void King_PieceColor_GenerateInstance() {
		assertThat(new King(PieceColor.BLACK)).isInstanceOf(King.class);
	}

	@ParameterizedTest
	@NullSource
	void King_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new King(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("킹의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new King(PieceColor.BLACK).getName()).isEqualTo("K");
		assertThat(new King(PieceColor.WHITE).getName()).isEqualTo("k");
	}
}