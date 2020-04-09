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
				Arguments.of(Direction.COL, Position.of("a8"), Position.of("a7")),
				Arguments.of(Direction.ROW, Position.of("a8"), Position.of("b8")),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, Position.of("a8"), Position.of("c6")),
				Arguments.of(Direction.POSITIVE_DIAGONAL, Position.of("c5"), Position.of("e7")),
				Arguments.of(Direction.NON_LINEAR, Position.of("a8"), Position.of("d7"))
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
				Arguments.of(Direction.COL, Position.of("a8"), Position.of("a7"), true),
				Arguments.of(Direction.ROW, Position.of("a8"), Position.of("b8"), true),
				Arguments.of(Direction.POSITIVE_DIAGONAL, Position.of("a8"), Position.of("c6"), false),
				Arguments.of(Direction.NEGATIVE_DIAGONAL, Position.of("c5"), Position.of("e7"), false)
		);
	}
}
