package chess.domain.position;

import org.junit.jupiter.api.Test;

class PositionTest {
	@Test
	void name() {
		Position of = Position.of(1, 1);
		System.out.println(of.getColumn());
		System.out.println(of.getRow());
	}
}