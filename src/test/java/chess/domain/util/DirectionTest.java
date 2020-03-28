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
        Position to = direction.move(from);
        assertThat(to).isEqualTo(expectedTo);
    }

    private static Stream<Arguments> getCasesForMoveByDirections() {
        return Stream.of(
                Arguments.of(new Position(5, 5), Direction.EAST, new Position(6, 5)),
                Arguments.of(new Position(5, 5), Direction.WEST, new Position(4, 5)),
                Arguments.of(new Position(5, 5), Direction.NORTH, new Position(5, 6)),
                Arguments.of(new Position(5, 5), Direction.SOUTH, new Position(5, 4)),
                Arguments.of(new Position(5, 5), Direction.NE, new Position(6, 6)),
                Arguments.of(new Position(5, 5), Direction.NW, new Position(4, 6)),
                Arguments.of(new Position(5, 5), Direction.SE, new Position(6, 4)),
                Arguments.of(new Position(5, 5), Direction.SW, new Position(4, 4)),
                Arguments.of(new Position(5, 5), Direction.NNE, new Position(6, 7)),
                Arguments.of(new Position(5, 5), Direction.NNW, new Position(4, 7)),
                Arguments.of(new Position(5, 5), Direction.SSE, new Position(6, 3)),
                Arguments.of(new Position(5, 5), Direction.SSW, new Position(4, 3)),
                Arguments.of(new Position(5, 5), Direction.EEN, new Position(7, 6)),
                Arguments.of(new Position(5, 5), Direction.EES, new Position(7, 4)),
                Arguments.of(new Position(5, 5), Direction.WWN, new Position(3, 6)),
                Arguments.of(new Position(5, 5), Direction.WWS, new Position(3, 4))
        );
    }
}
