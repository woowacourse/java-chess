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

class BishopTest {

	Position position;
	Bishop bishop;

	@BeforeEach
	void setUp() {
		position = Position.of(C, ONE);
		bishop = new Bishop(WHITE, position);
	}

	@Test
	@DisplayName("비숍은 Empty가 아니다")
	void notEmpty() {
		// then
		assertFalse(bishop.isEmpty());
	}

	@Test
	@DisplayName("비숍이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// then
		assertTrue(bishop.movable(LEFT_UP));
	}

	@Test
	@DisplayName("비숍이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// then
		assertFalse(bishop.movable(DOWN));
	}

	@Test
	@DisplayName("비숍은 끝까지 이동할 수 있다")
	void movableByCount() {
		// then
		assertTrue(bishop.movableByCount(5));
	}
}
