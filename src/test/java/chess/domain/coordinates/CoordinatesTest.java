package chess.domain.coordinates;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoordinatesTest {
	static Stream<Arguments> generatePosition() {
		return Stream.of(Arguments.of(Column.of("a"), null),
				Arguments.of(null, Row.of("1")),
				Arguments.of(null, null)
		);
	}

	static Stream<Arguments> generateDirection() {
		return Stream.of(Arguments.of(Direction.LEFT, Coordinates.of("A2")),
				Arguments.of(Direction.RIGHT, Coordinates.of("C2")),
				Arguments.of(Direction.UP, Coordinates.of("B3")),
				Arguments.of(Direction.DOWN, Coordinates.of("B1")),
				Arguments.of(Direction.RIGHT_DOWN, Coordinates.of("C1")),
				Arguments.of(Direction.RIGHT_UP, Coordinates.of("C3")),
				Arguments.of(Direction.LEFT_DOWN, Coordinates.of("A1")),
				Arguments.of(Direction.LEFT_UP, Coordinates.of("A3")));
	}

	@Test
	void ofTest() {
		assertThat(Coordinates.of("A1")).isInstanceOf(Coordinates.class);
	}

	@ParameterizedTest
	@MethodSource("generateDirection")
	void nextPositionTest(Direction direction, Coordinates expect) {
		assertThat(Coordinates.of("B2").next(direction)).isEqualTo(expect);
	}
}
