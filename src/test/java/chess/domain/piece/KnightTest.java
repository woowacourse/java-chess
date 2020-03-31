package chess.domain.piece;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightTest {
	Knight knight;

	@BeforeEach
	void setUp() {
		knight = new Knight(C3, Team.BLACK);
	}

	@Test
	void moveTo() {
		knight.moveTo(A2);
		assertThat(knight.getPosition()).isEqualTo(A2);
	}

	@Test
	void canNotMoveTo_Return_True() {
		Piece target = new Empty(B3);
		assertThat(knight.canNotMoveTo(target)).isTrue();
	}

	@Test
	void canNotMoveTo_Return_False() {
		Piece target = new Empty(A2);
		assertThat(knight.canNotMoveTo(target)).isFalse();
	}
}
