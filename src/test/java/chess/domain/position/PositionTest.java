package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {
	static Stream<Arguments> generatePosition() {
		return Stream.of(Arguments.of(Row.A, null),
			Arguments.of(null, Column.ONE),
			Arguments.of(null, null)
		);
	}

	@Test
	void ofTest() {
		assertThat(Position.of(Row.A, Column.ONE)).isInstanceOf(Position.class);
	}

	@DisplayName("Row 또는 Column이 null인 경우 예외 발생")
	@ParameterizedTest
	@MethodSource("generatePosition")
	void nullPositionTest(Row row, Column column) {
		assertThatThrownBy(() -> Position.of(row, column))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
