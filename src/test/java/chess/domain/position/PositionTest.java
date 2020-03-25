package chess.domain.position;

import static chess.domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {
	static Stream<Arguments> generatePosition() {
		return Stream.of(Arguments.of(Column.of("a"), null),
			Arguments.of(null, Row.of("1")),
			Arguments.of(null, null)
		);
	}

	static Stream<Arguments> generateDirection() {
		return Stream.of(Arguments.of(Direction.LEFT, A2),
			Arguments.of(Direction.RIGHT, C2),
			Arguments.of(Direction.UP, B3),
			Arguments.of(Direction.DOWN, B1),
			Arguments.of(Direction.RIGHT_DOWN, C1),
			Arguments.of(Direction.RIGHT_UP, C3),
			Arguments.of(Direction.LEFT_DOWN, A1),
			Arguments.of(Direction.LEFT_UP, A3));
	}

	@Test
	void ofTest() {
		assertThat(Position.of(Column.of("a"), Row.of("1"))).isInstanceOf(Position.class);
	}

	@DisplayName("Row 또는 Column이 null인 경우 예외 발생")
	@ParameterizedTest
	@MethodSource("generatePosition")
	void nullPositionTest(Column column, Row row) {
		assertThatThrownBy(() -> Position.of(column, row))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@MethodSource("generateDirection")
	void nextPositionTest(Direction direction, Position expect) {
		assertThat(B2.next(direction)).isEqualTo(expect);
	}
}
