package chess.domain;

import static chess.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PathTest {
	@ParameterizedTest
	@MethodSource("createPath")
	void create(List<Position> actual, List<Position> expected) {
		assertThat(actual).containsAll(expected);
	}

	private static Stream<Arguments> createPath() {
		return Stream.of(
			Arguments.of(Path.of(A1, A3).path(), List.of(A2, A3)),
			Arguments.of(Path.of(A1, C3).path(), List.of(B2, C3)),
			Arguments.of(Path.of(A1, D1).path(), List.of(B1, C1, D1)),
			Arguments.of(Path.of(A1, D2).path(), List.of(D2)),
			Arguments.of(Path.of(A1, B3).path(), List.of(B3))
		);
	}
}