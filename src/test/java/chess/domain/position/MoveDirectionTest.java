package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoveDirectionTest {

	@ParameterizedTest
	@CsvSource(value = {"N,d5,d6", "NE,d5,e6", "E,d5,e5", "SE,d5,e4", "S,d5,d4", "SW,d5,c4", "W,d5,c5", "NW,d5,c6"})
	void isSameDirection_SourcePositionAndTargetPosition_ReturnTrue(final MoveDirection moveDirection,
		final Position sourcePosition, final Position targetPosition) {
		assertThat(moveDirection.isSameDirectionFrom(sourcePosition, targetPosition)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"N,d5,e8", "NE,d5,a6", "E,c6,e5", "SE,e5,e4", "S,h5,d4", "SW,d5,c5", "W,f5,c8", "NW,d8,c6"})
	void isSameDirection_NotExistDirection_ReturnFalse(final MoveDirection moveDirection, final Position sourcePosition,
		final Position targetPosition) {
		assertThat(moveDirection.isSameDirectionFrom(sourcePosition, targetPosition)).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"N,d5", "NE,e5", "E,e4", "SE,e3", "S,d3", "SW,c3", "W,c4", "NW,c5"})
	void move_SourcePosition_MovedPosition(final MoveDirection moveDirection, final Position expected) {
		final Position position = Position.of("d4");

		assertThat(moveDirection.move(position)).isEqualTo(expected);
	}

}