package chess.domain.board;

import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

	@Test
	void addRank() {
		Direction direction = Direction.N;
		Optional<Rank> rank = direction.addRank(ONE);

		assertThat(rank).hasValue(TWO);
	}

	@Test
	void addFile() {
		Direction direction = Direction.E;
		Optional<File> file = direction.addFile(B);

		assertThat(file).hasValue(C);
	}

	@Test
	void findException() {
		assertThatThrownBy(() -> Direction.find(7, 1))
				.isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("해당 방향이 없습니다.");
	}

	@ParameterizedTest
	@MethodSource("createDirection")
	void find(int rank, int file, Direction direction) {
		assertThat(Direction.find(rank, file)).isEqualTo(direction);
	}

	private static Stream<Arguments> createDirection() {
		return Stream.of(
				Arguments.of(-1, 0, "S"),
				Arguments.of(0, -1, "W"),
				Arguments.of(1, 0, "N"),
				Arguments.of(0, 1, "E"),

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
