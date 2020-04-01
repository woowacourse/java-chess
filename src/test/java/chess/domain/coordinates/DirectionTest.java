package chess.domain.coordinates;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {
	static Stream<Arguments> generateCoordinatesAndDirection() {
		return Stream.of(
				Arguments.of(Coordinates.of("D4"), Coordinates.of("D5"), Direction.UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("D2"), Direction.DOWN),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("B4"), Direction.LEFT),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("G4"), Direction.RIGHT),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("B6"), Direction.LEFT_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("B2"), Direction.LEFT_DOWN),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("E5"), Direction.RIGHT_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("G1"), Direction.RIGHT_DOWN),

				Arguments.of(Coordinates.of("D4"), Coordinates.of("C6"), Direction.LEFT_UP_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("C2"), Direction.LEFT_DOWN_DOWN),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("B5"), Direction.LEFT_LEFT_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("B3"), Direction.LEFT_LEFT_DOWN),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("E6"), Direction.RIGHT_UP_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("E2"), Direction.RIGHT_DOWN_DOWN),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("F5"), Direction.RIGHT_RIGHT_UP),
				Arguments.of(Coordinates.of("D4"), Coordinates.of("F3"), Direction.RIGHT_RIGHT_DOWN)
		);
	}

	@DisplayName("두 좌표가 주어졌을 때 방향을 결정")
	@MethodSource("generateCoordinatesAndDirection")
	@ParameterizedTest
	void of(Coordinates from, Coordinates to, Direction expect) {
		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}
}
