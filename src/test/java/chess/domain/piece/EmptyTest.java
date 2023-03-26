package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class EmptyTest {

	Position position;
	Empty empty;

	@BeforeEach
	void setUp() {
		position = Position.of(B, FOUR);
		empty = new Empty(NONE, position);
	}

	@Test
	@DisplayName("빈 공간은 Empty이다")
	void isEmpty() {
		// then
		assertTrue(empty.isEmpty());
	}

	@Test
	@DisplayName("빈 공간은 이동할 수 없다")
	void notMovable() {
		// then
		assertFalse(empty.movable(LEFT_UP));
	}

	@Test
	@DisplayName("빈 공간은 한 칸도 이동할 수 없다")
	void neverMovable() {
		// then
		assertFalse(empty.movableByCount(1));
	}
}
