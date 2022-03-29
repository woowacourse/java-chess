package chess.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class DirectionTest {

    @ParameterizedTest
    @MethodSource("provideForPositionAndDirection")
    @DisplayName("출발지와 도착지의 방향을 구한다.")
    void getDirection(final Position from, final Position to, final Direction direction) {
        assertThat(Direction.find(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> provideForPositionAndDirection() {
        return Stream.of(
                Arguments.of("d4", "d6", Direction.N),
                Arguments.of("d4", "f6", Direction.NE),
                Arguments.of("d4", "f4", Direction.E),
                Arguments.of("d4", "f2", Direction.SE),
                Arguments.of("d4", "d2", Direction.S),
                Arguments.of("d4", "b2", Direction.SW),
                Arguments.of("d4", "a4", Direction.W),
                Arguments.of("d4", "b6", Direction.NW),

                Arguments.of("b1", "c3", Direction.NNE),
                Arguments.of("b1", "d2", Direction.ENE),
                Arguments.of("b3", "d2", Direction.ESE),
                Arguments.of("b3", "c1", Direction.SSE),
                Arguments.of("c3", "b1", Direction.SSW),
                Arguments.of("c3", "a2", Direction.WSW),
                Arguments.of("c3", "a4", Direction.WNW),
                Arguments.of("c3", "a4", Direction.WNW)
        );
    }

}
