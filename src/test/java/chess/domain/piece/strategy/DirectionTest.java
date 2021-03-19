package chess.domain.piece.strategy;

import chess.domain.position.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.position.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {
    @DisplayName("from을 통해 Direction 객체를 찾아오는지 확인")
    @ParameterizedTest
    @MethodSource
    void fromTest(Direction expected, int xDegree, int yDegree) {
        assertThat(Direction.of(xDegree, yDegree)).isEqualTo(expected);
    }

    private static Stream<Arguments> fromTest() {
        return Stream.of(
                Arguments.of(NORTH, 0, 1),
                Arguments.of(NORTHEAST, 1, 1),
                Arguments.of(EAST, 1, 0),
                Arguments.of(SOUTHEAST, 1, -1),
                Arguments.of(SOUTH, 0, -1),
                Arguments.of(SOUTHWEST, -1, -1),
                Arguments.of(WEST, -1, 0),
                Arguments.of(NORTHWEST, -1, 1),
                Arguments.of(NNE, 1, 2),
                Arguments.of(NNW, -1, 2),
                Arguments.of(SSE, 1, -2),
                Arguments.of(SSW, -1, -2),
                Arguments.of(EEN, 2, 1),
                Arguments.of(EES, 2, -1),
                Arguments.of(WWN, -2, 1),
                Arguments.of(WWS, -2, -1)
        );
    }
}