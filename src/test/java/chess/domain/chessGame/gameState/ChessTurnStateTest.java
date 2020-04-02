package chess.domain.chessGame.gameState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChessTurnStateTest {

	@Test
	void ChessTurnState_PieceColor_GenerateChessTurnState() {
		ChessTurnState chessTurnState = new WhiteTurnState();

		assertThat(chessTurnState).isInstanceOf(ChessTurnState.class);
	}

	@Test
	void shiftEndState_KingCaught_GenerateKingCaughtState() {
		ChessTurnState chessTurnState = new WhiteTurnState();

		assertThat(chessTurnState.shiftEndState(true)).isInstanceOf(KingCaughtState.class);
	}

	@Test
	void shiftEndState_KingNotCaught_GenerateEndState() {
		ChessTurnState chessTurnState = new BlackTurnState();

		assertThat(chessTurnState.shiftEndState(false)).isInstanceOf(EndState.class);
	}

}