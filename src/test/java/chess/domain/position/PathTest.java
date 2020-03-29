package chess.domain.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @ParameterizedTest
    @MethodSource("createPath")
    void create_With_Position(List<String> actual, List<String> expected) {
        assertThat(actual).containsAll(expected);
    }

    private static Stream<Arguments> createPath() {
        return Stream.of(
                Arguments.of(Path.of(A1, A3), List.of(A2)),
                Arguments.of(Path.of(A1, C3), List.of(B2)),
                Arguments.of(Path.of(A1, D1), List.of(B1, C1))
        );
    }

    @Test
    void create_With_String() {
        assertThat(Path.of(A1, A3)).containsAll(List.of(A2));
    }

    @Test
    void path_Return_EmptyList_When_Knight() {
        assertThat(Path.of(A1, D2)).isEmpty();
    }
}