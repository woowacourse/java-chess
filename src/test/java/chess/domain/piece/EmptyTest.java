package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class EmptyTest {

	@Test
	@DisplayName("빈 공간은 이동할 수 없다")
	void notMovable() {
		// given
		final Position position = Position.of(B, FOUR);
		final Empty empty = new Empty(NONE, position);

		// then
		assertFalse(empty.movable(Direction.LEFT_UP));
	}

	@Test
	@DisplayName("빈 공간은 한 칸도 이동할 수 없다")
	void neverMovable() {
		// given
		final Position position = Position.of(B, FOUR);
		final Empty empty = new Empty(NONE, position);

		// then
		assertFalse(empty.movableByCount(1));
	}
}
