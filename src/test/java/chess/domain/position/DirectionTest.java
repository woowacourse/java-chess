package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {
	@ParameterizedTest
	@MethodSource(value = "provideDirection")
	@DisplayName("올바른 방향을 찾는지 테스트")
	void findDirection(Direction expected, Position position1, Position position2) {
		assertThat(Direction.findDirection(position1, position2)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDirection() {
		return Stream.of(
				Arguments.of(Direction.COL, new Position("a8"), new Position("a7")),
				Arguments.of(Direction.ROW, new Position("a8"), new Position("b8")),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, new Position("a8"), new Position("c6")),
				Arguments.of(Direction.POSITIVE_DIAGONAL, new Position("c5"), new Position("e7")),
				Arguments.of(Direction.NON_LINEAR, new Position("a8"), new Position("d7"))
		);
	}

	@ParameterizedTest
	@MethodSource(value = "provideDirectionAndBoolean")
	@DisplayName("방향에 맞는지 테스트")
	void isMatch(Direction direction, Position position1, Position position2, boolean expected) {
		assertThat(direction.isMatch(position1, position2)).isEqualTo(expected);
	}

	private static Stream<Arguments> provideDirectionAndBoolean() {
		return Stream.of(
				Arguments.of(Direction.COL, new Position("a8"), new Position("a7"), true),
				Arguments.of(Direction.ROW, new Position("a8"), new Position("b8"), true),
				Arguments.of(Direction.POSITIVE_DIAGONAL, new Position("a8"), new Position("c6"), false),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, new Position("c5"), new Position("e7"), false)
		);
	}
}
