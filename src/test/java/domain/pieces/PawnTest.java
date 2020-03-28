package domain.pieces;

import domain.pieces.exceptions.CanNotAttackException;
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

class PawnTest {
	Pawn pawn;
	Pawn pawnOnceMoved;

	@BeforeEach
	void setUp() {
		pawn = createPawn("black", "a1");
		pawnOnceMoved = createPawnOnceMoved("black", "a1");
	}

	@Test
	void move() {
		Point point = createPoint("a2");
		Pawn expect = createPawnOnceMoved("black", "a2");

		assertThat(pawn.move(point)).isEqualTo(expect);
	}

	@Test
	void canMove() {
		Direction direction = createDirection("n");

		assertThatThrownBy(() -> pawn.canMove(direction))
				.isInstanceOf(CanNotMoveException.class);
	}

	@Test
	void canAttack() {
		Direction direction = createDirection("ne");
		Piece other = createPawn("white", "a2");

		assertThatThrownBy(() -> pawn.canAttack(direction, other))
				.isInstanceOf(CanNotAttackException.class);
	}

	@Test
	void canReach() {
		Distance distance = createDistance("vertical_two");

		assertThatThrownBy(() -> pawnOnceMoved.canReach(distance))
				.isInstanceOf(CanNotReachException.class);
	}
}