package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class KingCaughtStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void KingCaughtState_PieceColor_GenerateInstance(PieceColor pieceColor) {
		assertThat(new KingCaughtState(pieceColor)).isInstanceOf(KingCaughtState.class);
	}

	@Test
	void getPieceColor_PieceColor_ReturnPieceColor() {
		final ChessEndState kingCaughtState = new KingCaughtState(PieceColor.BLACK);

		assertThat(kingCaughtState.getPieceColor()).isEqualTo(PieceColor.BLACK);
	}

	@Test
	void isKingCaughtState_KingCaughtState_ReturnTrue() {
		assertThat(new KingCaughtState(PieceColor.BLACK).isKingCaughtState()).isTrue();
	}

}