package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class KingTest {
	@Test
	@DisplayName("킹이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// given
		final Position position = Position.of(E, ONE);
		final King king = new King(WHITE, position);

		// then
		assertTrue(king.movable(Direction.LEFT_UP));
	}

	@Test
	@DisplayName("킹이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// given
		final Position position = Position.of(E, ONE);
		final King king = new King(WHITE, position);

		// then
		assertFalse(king.movable(Direction.KNIGHT_DOWN_LEFT));
	}

	@Test
	@DisplayName("킹은 한 칸만 이동할 수 있다")
	void movableByCount() {
		// given
		final Position position = Position.of(E, ONE);
		final King king = new King(WHITE, position);

		// then
		assertTrue(king.movableByCount(0));
	}

	@Test
	@DisplayName("킹은 두 칸 이상 이동할 수 없다")
	void notMovableByCountOver2() {
		// given
		final Position position = Position.of(E, ONE);
		final King king = new King(WHITE, position);

		// then
		assertFalse(king.movableByCount(2));
	}
}
