package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessPiece.pieceType.PieceColor;

class NonLeapableChessPieceTest {

	@Test
	void canLeap_NonLeapableChessPiece_ReturnFalse() {
		assertThat(new Queen(PieceColor.WHITE).canLeap()).isFalse();
	}

}