package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.chessPiece.pieceType.PieceColor;

class BlackTurnStateTest {

	@Test
	void BlackTurnState_BlackPieceColor_GenerateInstance() {
		assertThat(new BlackTurnState()).isInstanceOf(BlackTurnState.class);
	}

	@Test
	void shiftNextTurnState_WhitePieceColor_ReturnBlackTurnState() {
		assertThat(new BlackTurnState().shiftNextTurnState()).isInstanceOf(WhiteTurnState.class);
	}

	@Test
	void getPieceColor_BlackTurnState_ReturnBlackPieceColor() {
		assertThat(new BlackTurnState().getPieceColor()).isEqualTo(PieceColor.BLACK);
	}

}