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
	void path(List<String> actual, List<String> expected) {
		assertThat(actual).containsAll(expected);
	}

	private static Stream<Arguments> createPath() {
		return Stream.of(
			Arguments.of(Path.of(A1, A3).path(), List.of("a2")),
			Arguments.of(Path.of(A1, C3).path(), List.of("b2")),
			Arguments.of(Path.of(A1, D1).path(), List.of("b1", "c1"))
		);
	}

	@Test
	void path_Return_EmptyList_When_Knight() {
		assertThat(Path.of(A1, D2).path()).isEmpty();
	}
}