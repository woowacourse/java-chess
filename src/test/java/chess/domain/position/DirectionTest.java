package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @DisplayName("#calculate() : should return Direction as to Position 'from' and 'to")
    @MethodSource({"getCasesForCalculate"})
    void calculate(Position from, Position to, Direction expected) {
        assertThat(Direction.calculate(from, to)).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForCalculate() {
        return Stream.of(
                Arguments.of(Position.of(2,2), Position.of(2,2), Direction.STAY),
                Arguments.of(Position.of(2,2), Position.of(2,3), Direction.NORTH),
                Arguments.of(Position.of(2,2), Position.of(2,1), Direction.SOUTH),
                Arguments.of(Position.of(2,2), Position.of(3,2), Direction.EAST),
                Arguments.of(Position.of(2,2), Position.of(1,2), Direction.WEST),
                Arguments.of(Position.of(2,2), Position.of(3,3), Direction.NORTH_EAST),
                Arguments.of(Position.of(2,2), Position.of(1,3), Direction.NORTH_WEST),
                Arguments.of(Position.of(2,2), Position.of(3,1), Direction.SOUTH_EAST),
                Arguments.of(Position.of(2,2), Position.of(1,1), Direction.SOUTH_WEST),
                Arguments.of(Position.of(1,1), Position.of(2,3), Direction.UNKNOWN)
        );
    }
}