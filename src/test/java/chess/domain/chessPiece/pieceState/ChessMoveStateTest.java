package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChessMoveStateTest {

	@Test
	void shiftNextState_InitialState_ReturnMovedState() {
		assertThat(new InitialState().shiftNextState()).isInstanceOf(MovedState.class);
	}

	@Test
	void shiftNextState_MovedState_ReturnThis() {
		assertThat(new MovedState().shiftNextState()).isInstanceOf(MovedState.class);
	}

}