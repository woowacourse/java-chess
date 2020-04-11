package chess.domain.piece;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopTest {
	Bishop bishop;

	@BeforeEach
	void setUp() {
		bishop = new Bishop(C3, Team.BLACK);
	}

	@Test
	void moveTo_FromC3() {
		bishop.moveTo(H8);
		assertThat(bishop.getPosition()).isEqualTo(H8);
	}

	@Test
	void canNotMoveTo_Return_True_When_OutOfMovableArea() {
		Piece target = new Empty(C4);
		assertThat(bishop.canNotMoveTo(target)).isTrue();
	}

	@Test
	void canNotMoveTo_Return_False_When_InMovableArea() {
		Piece target = new Empty(B4);
		assertThat(bishop.canNotMoveTo(target)).isFalse();
	}
}