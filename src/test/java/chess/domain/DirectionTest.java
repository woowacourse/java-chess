package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

	@ParameterizedTest
	@MethodSource("createDirection")
	void findDirection(String start, String end, Direction expected) {
		assertThat(Direction.of(Position.of(start), Position.of(end))).isEqualTo(expected);
	}

	private static Stream<Arguments> createDirection() {
		return Stream.of(
			Arguments.of("c3", "d3", Direction.E),
			Arguments.of("c3", "a3", Direction.W),
			Arguments.of("c3", "c2", Direction.S),
			Arguments.of("c3", "c4", Direction.N),
			Arguments.of("c3", "d4", Direction.NE),
			Arguments.of("c3", "b4", Direction.NW),
			Arguments.of("c3", "b2", Direction.SW),
			Arguments.of("c3", "d2", Direction.ES)
		);
	}

	@Test
	void move_When_DirectionIsNorth() {
		Position expected = Position.of("c4");
		assertThat(Direction.N.move(Position.of("c3"))).isEqualTo(expected);
	}
}