package chess.domain.util;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    @DisplayName("direction 으로 이동 여부 확인")
    @ParameterizedTest
    @MethodSource("getCasesForMoveByDirections")
    void move(Position from, Direction direction, Position expectedTo) {
        Position to = from.moveBy(direction);
        assertThat(to).isEqualTo(expectedTo);
    }

    private static Stream<Arguments> getCasesForMoveByDirections() {
        return Stream.of(
                Arguments.of(Position.of("e5"), Direction.EAST, Position.of("f5")),
                Arguments.of(Position.of("e5"), Direction.WEST, Position.of("d5")),
                Arguments.of(Position.of("e5"), Direction.NORTH, Position.of("e6")),
                Arguments.of(Position.of("e5"), Direction.SOUTH, Position.of("e4")),
                Arguments.of(Position.of("e5"), Direction.NORTH_EAST, Position.of("f6")),
                Arguments.of(Position.of("e5"), Direction.NORTH_WEST, Position.of("d6")),
                Arguments.of(Position.of("e5"), Direction.SOUTH_EAST, Position.of("f4")),
                Arguments.of(Position.of("e5"), Direction.SOUTH_WEST, Position.of("d4")),
                Arguments.of(Position.of("e5"), Direction.NORTH_NORTH_EAST, Position.of("f7")),
                Arguments.of(Position.of("e5"), Direction.NORTH_NORTH_WEST, Position.of("d7")),
                Arguments.of(Position.of("e5"), Direction.SOUTH_SOUTH_EAST, Position.of("f3")),
                Arguments.of(Position.of("e5"), Direction.SOUTH_SOUTH_WEST, Position.of("d3")),
                Arguments.of(Position.of("e5"), Direction.EAST_EAST_NORTH, Position.of("g6")),
                Arguments.of(Position.of("e5"), Direction.EAST_EAST_SOUTH, Position.of("g4")),
                Arguments.of(Position.of("e5"), Direction.WEST_WEST_NORTH, Position.of("c6")),
                Arguments.of(Position.of("e5"), Direction.WEST_WEST_SOUTH, Position.of("c4"))
        );
    }
}
