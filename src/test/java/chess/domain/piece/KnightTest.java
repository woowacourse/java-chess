package chess.domain.piece;

import static chess.domain.color.Color.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.move.Direction;
import chess.domain.position.Position;

class KnightTest {

	Position position;
	Knight knight;

	@BeforeEach
	void setUp() {
		position = Position.of(B, ONE);
		knight = new Knight(WHITE, position);
	}

	@Test
	@DisplayName("나이트는 Empty가 아니다")
	void notEmpty() {
		// then
		assertFalse(knight.isEmpty());
	}

	@Test
	@DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
	void movable() {
		// then
		assertTrue(knight.movable(Direction.KNIGHT_LEFT_UP));
	}

	@Test
	@DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// then
		assertFalse(knight.movable(Direction.DOWN));
	}

	@Test
	@DisplayName("나이트는 항상 한 칸 이상 이동한다")
	void movableByCount() {
		// then
		assertTrue(knight.movableByCount(5));
	}
}
