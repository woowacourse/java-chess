package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static testAssistant.creationAssistant.*;

class RookTest {
	Rook rook;

	@BeforeEach
	void setUp() {
		rook = createRook("black", "a1");
	}

	@Test
	void move() {
		Point point = createPoint("a2");
		Rook expect = createRook("black", "a2");

		assertThat(rook.move(point)).isEqualTo(expect);
	}

	@Test
	void canMove() {
		Direction direction = createDirection("ne");

		assertThatThrownBy(() -> rook.validateMoveDirection(direction))
				.isInstanceOf(CanNotMoveException.class);
	}
}