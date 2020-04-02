package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WhiteTurnStateTest {

	@Test
	void WhiteTurnState_WhitePieceColor_GenerateInstance() {
		assertThat(new WhiteTurnState()).isInstanceOf(WhiteTurnState.class);
	}

	@Test
	void shiftNextTurnState_WhitePieceColor_ReturnBlackTurnState() {
		assertThat(new WhiteTurnState().shiftNextTurnState()).isInstanceOf(BlackTurnState.class);
	}

}