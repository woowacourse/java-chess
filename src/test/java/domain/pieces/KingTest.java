package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static testAssistant.creationAssistant.*;

class KingTest {
	King king;

	@BeforeEach
	void setUp() {
		king = createKing("black", "a1");
	}

	@Test
	void move() {
		Point point = createPoint("a2");
		King expect = createKing("black", "a2");

		assertThat(king.move(point)).isEqualTo(expect);
	}

	@Test
	void canMove_ThrowException() {
		Direction direction = createDirection("knight");

		assertThatThrownBy(() -> king.canMove(direction))
				.isInstanceOf(CanNotMoveException.class);
	}

	@Test
	void canReach() {
		Distance distance = createDistance("vertical_two");

		assertThatThrownBy(() -> king.canReach(distance))
				.isInstanceOf(CanNotReachException.class);

	}
}