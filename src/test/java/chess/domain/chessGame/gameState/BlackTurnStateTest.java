package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BlackTurnStateTest {

	@Test
	void BlackTurnState_BlackPieceColor_GenerateInstance() {
		assertThat(new BlackTurnState()).isInstanceOf(BlackTurnState.class);
	}

	@Test
	void shiftNextTurnState_WhitePieceColor_ReturnBlackTurnState() {
		assertThat(new BlackTurnState().shiftNextTurnState()).isInstanceOf(WhiteTurnState.class);
	}

}