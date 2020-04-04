package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

public class MoveManagerTest {

	@Test
	void validateKnightMoveTest() {
		MoveManager moveManager = new MoveManager(Position.of(3, 3));
		assertThatThrownBy(() -> moveManager.validateKnightMove(Position.of(4, 2)))
			.isInstanceOf(IllegalArgumentException.class);

	}
}
