package chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
	@Test
	void 생성() {
		Position position = new Position(1, 8);
		assertThat(position).isEqualTo(new Position(1, 8));
	}

	@Test
	void 왼쪽_위로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(1, 5);

		assertTrue(start.canMove(end, Direction.LEFT_TOP));
	}

	@Test
	void 왼쪽_위로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.LEFT_TOP));
	}

	@Test
	void 위로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(3, 8);

		assertTrue(start.canMove(end, Direction.TOP));
	}

	@Test
	void 위로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(3, 1);

		assertFalse(start.canMove(end, Direction.TOP));
	}

	@Test
	void 오른쪽_위로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(4, 4);

		assertTrue(start.canMove(end, Direction.RIGHT_TOP));
	}

	@Test
	void 오른쪽_위로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 4);

		assertFalse(start.canMove(end, Direction.RIGHT_TOP));
	}

	@Test
	void 왼쪽으로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(1, 3);

		assertTrue(start.canMove(end, Direction.LEFT));
	}

	@Test
	void 왼쪽으로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.LEFT));
	}

	@Test
	void 오른쪽으로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(8, 3);

		assertTrue(start.canMove(end, Direction.RIGHT));
	}

	@Test
	void 오른쪽으로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.RIGHT));
	}

	@Test
	void 왼쪽_아래로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(1, 1);

		assertTrue(start.canMove(end, Direction.LEFT_BOTTOM));
	}

	@Test
	void 왼쪽_아래로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.LEFT_BOTTOM));
	}

	@Test
	void 아래로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(3, 1);

		assertTrue(start.canMove(end, Direction.BOTTOM));
	}

	@Test
	void 아래로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.BOTTOM));
	}

	@Test
	void 오른쪽_아래로_갈_수_있는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(5, 1);

		assertTrue(start.canMove(end, Direction.RIGHT_BOTTOM));
	}

	@Test
	void 오른쪽_아래로_갈_수_없는_경우() {
		Position start = new Position(3, 3);
		Position end = new Position(2, 5);

		assertFalse(start.canMove(end, Direction.RIGHT_BOTTOM));
	}

	@Test
	void 위로_이동() {
		Position position = new Position(1,2);
		assertThat(position.move(Direction.TOP)).isEqualTo(new Position(1,3));
	}
}