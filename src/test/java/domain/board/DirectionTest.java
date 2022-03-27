package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Direction;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

	@Test
	void addRow() {
		Direction direction = Direction.N;

		assertThat(direction.addRow(2)).isEqualTo(3);
	}

	@Test
	void addColumn() {
		Direction direction = Direction.N;

		assertThat(direction.addColumn(2)).isEqualTo(2);
	}

	@Test
	void findException() {
		assertThatThrownBy(() -> Direction.find(8, 8))
				.isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("해당 방향이 없습니다.");
	}

	@ParameterizedTest(name = "{index} - {0}, {1} : {2}")
	@MethodSource("createDirection")
	void find(int row, int column, Direction direction) {
		assertThat(Direction.find(row, column)).isEqualTo(direction);
	}

	private static Stream<Arguments> createDirection() {
		return Stream.of(
				Arguments.of(-1, 0, "S"),
				Arguments.of(0, -1, "W"),
				Arguments.of(1, 0, "N"),
				Arguments.of(0, 1, "E"),

				Arguments.of(-2, 0, "SS"),
				Arguments.of(2, 0, "NN"),

				Arguments.of(-1, -1, "SW"),
				Arguments.of(-1, 1, "SE"),
				Arguments.of(1, -1, "NW"),
				Arguments.of(1, 1, "NE"),

				Arguments.of(2, 1, "NNE"),
				Arguments.of(2, -1, "NNW"),
				Arguments.of(-2, 1, "SSE"),
				Arguments.of(-2, -1, "SSW"),
				Arguments.of(1, 2, "EEN"),
				Arguments.of(-1, 2, "EES"),
				Arguments.of(1, -2, "WWN"),
				Arguments.of(-1, -2, "WWS")
		);
	}
}
