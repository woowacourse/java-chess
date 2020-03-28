package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static testAssistant.creationAssistant.*;

class QueenTest {
	Queen queen = createQueen("black", "a1");

	@Test
	void move() {
		Point point = createPoint("a2");
		Queen expect = createQueen("black", "a2");

		assertThat(queen.move(point)).isEqualTo(expect);
	}

	@Test
	void canMove() {
		Direction direction = createDirection("knight");

		assertThatThrownBy(() -> queen.canMove(direction))
				.isInstanceOf(CanNotMoveException.class);
	}
}