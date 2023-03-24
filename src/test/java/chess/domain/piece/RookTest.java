package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class RookTest {

	@Test
	@DisplayName("룩이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// given
		final Position position = Position.of(A, ONE);
		final Rook rook = new Rook(WHITE, position);

		// then
		assertTrue(rook.movable(Direction.LEFT));
	}

	@Test
	@DisplayName("룩이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// given
		final Position position = Position.of(A, ONE);
		final Rook rook = new Rook(WHITE, position);

		// then
		assertFalse(rook.movable(Direction.LEFT_UP));
	}

	@Test
	@DisplayName("룩은 끝까지 이동할 수 있다")
	void movableByCount() {
		// given
		final Position position = Position.of(A, ONE);
		final Rook rook = new Rook(WHITE, position);

		// then
		assertTrue(rook.movableByCount(5));
	}
}
