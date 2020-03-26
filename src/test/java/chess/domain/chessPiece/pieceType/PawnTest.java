package chess.domain.chessPiece.pieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessPiece.PieceColor;

class PawnTest {
	@Test
	void Pawn_PieceColor_GenerateInstance() {
		assertThat(new Pawn(PieceColor.BLACK)).isInstanceOf(Pawn.class);
	}

	@ParameterizedTest
	@NullSource
	void Pawn_NullPieceColor_ExceptionThrown(PieceColor pieceColor) {
		assertThatThrownBy(() -> new Pawn(pieceColor))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("폰의 피스 색상이 null입니다.");
	}

	@Test
	void getName_ReturnName() {
		assertThat(new Pawn(PieceColor.BLACK).getName()).isEqualTo("P");
		assertThat(new Pawn(PieceColor.WHITE).getName()).isEqualTo("p");
	}
}