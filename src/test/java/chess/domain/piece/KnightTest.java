package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class KnightTest {
	@Test
	@DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
	void movable() {
		// given
		final Position position = Position.of(B, ONE);
		final Knight knight = new Knight(WHITE, position);

		// then
		assertTrue(knight.movable(Direction.KNIGHT_LEFT_UP));
	}

	@Test
	@DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// given
		final Position position = Position.of(B, ONE);
		final Knight knight = new Knight(WHITE, position);

		// then
		assertFalse(knight.movable(Direction.DOWN));
	}

	@Test
	@DisplayName("나이트는 항상 한 칸 이상 이동한다")
	void movableByCount() {
		// given
		final Position position = Position.of(B, ONE);
		final Knight knight = new Knight(WHITE, position);

		// then
		assertTrue(knight.movableByCount(5));
	}
}
