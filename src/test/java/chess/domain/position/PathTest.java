package chess.domain.position;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PathTest {

	@ParameterizedTest
	@MethodSource("createPath")
	void create_With_Position(List<Position> actual, List<Position> expected) {
		assertThat(actual).containsAll(expected);
	}

	private static Stream<Arguments> createPath() {
		return Stream.of(
			Arguments.of(Path.of(A1, A3).toList(), List.of(A2)),
			Arguments.of(Path.of(A1, C3).toList(), List.of(B2)),
			Arguments.of(Path.of(A1, D1).toList(), List.of(B1, C1))
		);
	}

	@Test
	void create_With_String() {
		assertThat(Path.of(A1, A3).toList()).containsAll(List.of(A2));
	}

	@Test
	void path_Return_EmptyList_When_Knight() {
		assertThat(Path.of(A1, D2).toList()).isEmpty();
	}
}