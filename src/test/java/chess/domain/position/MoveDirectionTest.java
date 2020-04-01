package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveDirectionTest {
	@Test
	void isSameDirection_SourcePosition_SamePositionResult() {
		Position sourcePosition = Position.of("d4");
		Position targetPosition = Position.of("d6");

		assertThat(MoveDirection.N.isSameDirectionFrom(sourcePosition, targetPosition)).isTrue();
	}

	@Test
	void move_SourcePosition_MovedPosition() {
		Position sourcePosition = Position.of("d4");

		assertThat(MoveDirection.N.moveDirection(sourcePosition)).isEqualTo(Position.of("d5"));
		assertThat(MoveDirection.NE.moveDirection(sourcePosition)).isEqualTo(Position.of("e5"));
		assertThat(MoveDirection.E.moveDirection(sourcePosition)).isEqualTo(Position.of("e4"));
		assertThat(MoveDirection.SE.moveDirection(sourcePosition)).isEqualTo(Position.of("e3"));
		assertThat(MoveDirection.S.moveDirection(sourcePosition)).isEqualTo(Position.of("d3"));
		assertThat(MoveDirection.SW.moveDirection(sourcePosition)).isEqualTo(Position.of("c3"));
		assertThat(MoveDirection.W.moveDirection(sourcePosition)).isEqualTo(Position.of("c4"));
		assertThat(MoveDirection.NW.moveDirection(sourcePosition)).isEqualTo(Position.of("c5"));
	}
}