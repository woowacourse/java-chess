package chess.domain.board;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @ParameterizedTest
    @MethodSource("createColumn")
    void calculateScoreOf(List<String> column, double expected) {
        assertThat(Score.calculateScoreOf(column)).isEqualTo(expected);
    }

    private static Stream<Arguments> createColumn() {
        return Stream.of(
                Arguments.of(List.of("Q", "P"), 10.0),
                Arguments.of(List.of("K", "P", "N"), 3.5),
                Arguments.of(List.of("P", "P", "P"), 1.5)
        );
    }
}