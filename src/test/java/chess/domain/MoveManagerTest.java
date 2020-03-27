package chess.domain;

import static chess.domain.Direction.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

public class MoveManagerTest {

	@Test
	void validateMoveTest() {
		MoveManager moveManager = new MoveManager(Position.of(3, 3));
		List<Direction> directions = Arrays.asList(UP, LEFT, RIGHT, DOWN, RIGHT_DOWN,
			RIGHT_UP, LEFT_DOWN, LEFT_UP);

		assertThatThrownBy(() -> moveManager.validateMove(Position.of(5, 5), directions))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void validateMoveTest2() {
		MoveManager moveManager = new MoveManager(Position.of(3, 3));
		List<Direction> directions = Arrays.asList(UP, LEFT, RIGHT, DOWN, RIGHT_DOWN,
			RIGHT_UP, LEFT_DOWN, LEFT_UP);

		assertThatThrownBy(() -> moveManager.validateMove(Position.of(1, 3), directions))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void validateKnightMoveTest() {
		MoveManager moveManager = new MoveManager(Position.of(3, 3));
		assertThatThrownBy(() -> moveManager.validateKnightMove(Position.of(4, 2)))
			.isInstanceOf(IllegalArgumentException.class);

	}
}
