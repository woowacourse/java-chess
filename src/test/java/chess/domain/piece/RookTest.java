package chess.domain.piece;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RookTest {

	@Test
	void moveTo_When_Success() {
		Piece rook = new Rook(C3, Team.BLACK);
		rook.moveTo(H3);

		assertThat(rook.getPosition()).isEqualTo(H3);
	}

	@Test
	void moveTo_When_Fail() {
		Piece rook = new Rook(C3, Team.BLACK);
		assertThat(rook.canNotMoveTo(new Empty(D4))).isTrue();
	}
}