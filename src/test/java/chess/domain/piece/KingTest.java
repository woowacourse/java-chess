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

class KingTest {

	Position position;
	King king;

	@BeforeEach
	void setUp() {
		position = Position.of(E, ONE);
		king = new King(WHITE, position);
	}

	@Test
	@DisplayName("킹은 Empty가 아니다")
	void notEmpty() {
		// then
		assertFalse(king.isEmpty());
	}

	@Test
	@DisplayName("킹이 이동할 수 있으면 true를 반환한다")
	void movable() {
		// then
		assertTrue(king.movable(Direction.LEFT_UP));
	}

	@Test
	@DisplayName("킹이 이동할 수 없으면 false를 반환한다")
	void notMovable() {
		// then
		assertFalse(king.movable(Direction.KNIGHT_DOWN_LEFT));
	}

	@Test
	@DisplayName("킹은 한 칸만 이동할 수 있다")
	void movableByCount() {
		// then
		assertTrue(king.movableByCount(0));
	}

	@Test
	@DisplayName("킹은 두 칸 이상 이동할 수 없다")
	void notMovableByCountOver2() {
		// then
		assertFalse(king.movableByCount(2));
	}
}
