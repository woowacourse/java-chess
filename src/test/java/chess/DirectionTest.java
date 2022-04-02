package chess;

import chess.model.position.Direction;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    static Stream<Arguments> getDirections() {
        return Stream.of(
                Arguments.of("d6", Direction.N),
                Arguments.of("g3", Direction.E),
                Arguments.of("a3", Direction.W),
                Arguments.of("d2", Direction.S),
                Arguments.of("b5", Direction.NW),
                Arguments.of("g6", Direction.NE),
                Arguments.of("c2", Direction.SW),
                Arguments.of("f1", Direction.SE),
                Arguments.of("e5", Direction.NNE),
                Arguments.of("c5", Direction.NNW),
                Arguments.of("e1", Direction.SSE),
                Arguments.of("c1", Direction.SSW),
                Arguments.of("f4", Direction.EEN),
                Arguments.of("f2", Direction.EES),
                Arguments.of("b4", Direction.WWN),
                Arguments.of("b2", Direction.WWS)
        );
    }

    @ParameterizedTest
    @MethodSource("getDirections")
    @DisplayName("source와 target의 방향이 올바른지 확인한다.")
    void getDirectionTest(String targetPos, Direction direction) {
        Position source = Position.from("d3");
        Position target = Position.from(targetPos);
        Direction expected = Direction.of(source, target);

        assertThat(expected).isEqualTo(direction);
    }
}
