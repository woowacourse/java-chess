package chess.domain.piece;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.position.Position;

class RookTest {
	static Stream<Arguments> generatePositions() {
		return Stream.of(
			Arguments.of(A1, A3, true),
			Arguments.of(A1, C1, true),
			Arguments.of(A1, B2, false),
			Arguments.of(A1, B3, false),
			Arguments.of(A1, A1, false)
		);
	}

	@Test
	void initTest() {
		assertThat(new Rook(Color.WHITE)).isInstanceOf(Rook.class);
	}

	@ParameterizedTest
	@MethodSource("generatePositions")
	void canMoveTest(Position currentPosition, Position destination, boolean expect) {
		Rook rook = new Rook(Color.WHITE);

		assertThat(rook.canMove(currentPosition, destination)).isEqualTo(expect);
	}
}
