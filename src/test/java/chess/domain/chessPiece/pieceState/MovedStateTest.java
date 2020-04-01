package chess.domain.chessPiece.pieceState;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovedStateTest {

	@Test
	void getPawnMovableRange_ReturnMovedStatePawnMovableRange() {
		assertThat(new MovedState().getPawnMovableRange()).isEqualTo(1);
	}

}