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

class RookTest {

	Position position;
	Rook rook;

	@BeforeEach
	void setUp() {
		position = Position.of(A, ONE);
		rook = new Rook(WHITE, position);
	}

	@Test
	@DisplayName("룩은 Empty가 아니다")
	void notEmpty() {
		// then
		assertFalse(rook.isEmpty());
	}

	@Test
	@DisplayName("룩이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// then
		assertTrue(rook.movable(LEFT));
	}

	@Test
	@DisplayName("룩이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// then
		assertFalse(rook.movable(LEFT_UP));
	}

	@Test
	@DisplayName("룩은 끝까지 이동할 수 있다")
	void movableByCount() {
		// then
		assertTrue(rook.movableByCount(5));
	}
}
