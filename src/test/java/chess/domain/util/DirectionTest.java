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
                Arguments.of(new Position(5, 5), Direction.EAST, new Position(6, 5)),
                Arguments.of(new Position(5, 5), Direction.WEST, new Position(4, 5)),
                Arguments.of(new Position(5, 5), Direction.NORTH, new Position(5, 6)),
                Arguments.of(new Position(5, 5), Direction.SOUTH, new Position(5, 4)),
                Arguments.of(new Position(5, 5), Direction.NORTH_EAST, new Position(6, 6)),
                Arguments.of(new Position(5, 5), Direction.NORTH_WEST, new Position(4, 6)),
                Arguments.of(new Position(5, 5), Direction.SOUTH_EAST, new Position(6, 4)),
                Arguments.of(new Position(5, 5), Direction.SOUTH_WEST, new Position(4, 4)),
                Arguments.of(new Position(5, 5), Direction.NORTH_NORTH_EAST, new Position(6, 7)),
                Arguments.of(new Position(5, 5), Direction.NORTH_NORTH_WEST, new Position(4, 7)),
                Arguments.of(new Position(5, 5), Direction.SOUTH_SOUTH_EAST, new Position(6, 3)),
                Arguments.of(new Position(5, 5), Direction.SOUTH_SOUTH_WEST, new Position(4, 3)),
                Arguments.of(new Position(5, 5), Direction.EAST_EAST_NORTH, new Position(7, 6)),
                Arguments.of(new Position(5, 5), Direction.EAST_EAST_SOUTH, new Position(7, 4)),
                Arguments.of(new Position(5, 5), Direction.WEST_WEST_NORTH, new Position(3, 6)),
                Arguments.of(new Position(5, 5), Direction.WEST_WEST_SOUTH, new Position(3, 4))
        );
    }
}
