package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InitialStateTest {

	@Test
	void shiftNextState_InitialState_ReturnMovedState() {
		assertThat(new InitialState().shiftNextState()).isInstanceOf(MovedState.class);
	}

	@Test
	void getPawnMovableRange_ReturnInitialStatePawnMovableRange() {
		assertThat(new InitialState().getPawnMovableRange()).isEqualTo(2);
	}

}