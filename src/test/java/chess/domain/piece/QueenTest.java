package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class QueenTest {
	@Test
	@DisplayName("퀸이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// given
		final Position position = Position.of(D, ONE);
		final Queen queen = new Queen(WHITE, position);

		// then
		assertTrue(queen.movable(Direction.LEFT_DOWN));
	}

	@Test
	@DisplayName("퀸이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// given
		final Position position = Position.of(D, ONE);
		final Queen queen = new Queen(WHITE, position);

		// then
		assertFalse(queen.movable(Direction.KNIGHT_LEFT_UP));
	}

	@Test
	@DisplayName("퀸은 끝까지 이동할 수 있다")
	void movableByCount() {
		// given
		final Position position = Position.of(D, ONE);
		final Queen queen = new Queen(WHITE, position);

		// then
		assertTrue(queen.movableByCount(5));
	}
}
