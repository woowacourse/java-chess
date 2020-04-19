package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import chess.domain.chessPiece.pieceType.PieceColor;

class EndStateTest {

	@ParameterizedTest
	@EnumSource(PieceColor.class)
	void EndState_PieceColor_GenerateInstance(final PieceColor pieceColor) {
		assertThat(new EndState(pieceColor)).isInstanceOf(EndState.class);
	}

	@Test
	void getPieceColor_PieceColor_ReturnPieceColor() {
		final ChessEndState chessEndState = new EndState(PieceColor.BLACK);

		assertThat(chessEndState.getPieceColor()).isEqualTo(PieceColor.BLACK);
	}

	@Test
	void isKingCaughtState_EndState_ReturnFalse() {
		assertThat(new EndState(PieceColor.BLACK).isKingCaughtState()).isFalse();
	}

}