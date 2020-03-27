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
	void create_With_Position(List<String> actual, List<String> expected) {
		assertThat(actual).containsAll(expected);
	}

	private static Stream<Arguments> createPath() {
		return Stream.of(
			Arguments.of(Path.valueOf(A1, A3), List.of("a2")),
			Arguments.of(Path.valueOf(A1, C3), List.of("b2")),
			Arguments.of(Path.valueOf(A1, D1), List.of("b1", "c1"))
		);
	}

	@Test
	void create_With_String() {
		assertThat(Path.valueOf("a1", "a3")).containsAll(List.of("a2"));
	}

	@Test
	void path_Return_EmptyList_When_Knight() {
		assertThat(Path.valueOf(A1, D2)).isEmpty();
	}
}