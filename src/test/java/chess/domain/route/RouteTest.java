package chess.domain.route;

import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RouteTest {

    @Test
    void emptyRoute() {
        assertThat(Route.emptyRoute()).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("createPositionAndRoute")
    void 시작하는_위치에서_이동할_위치까지_거치는_경로를_반환(Position fromPosition, Direction direction, PieceType pieceType, Route expected) {
        assertThat(Route.findRoute(fromPosition, direction, pieceType))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> createPositionAndRoute() {
        return Stream.of(
                Arguments.of(Position.of("b1"), Direction.NORTH, PieceType.ROOK,
                        new Route(Arrays.asList(
                                Position.of("b2"),
                                Position.of("b3"),
                                Position.of("b4"),
                                Position.of("b5"),
                                Position.of("b6"),
                                Position.of("b7"),
                                Position.of("b8")))),
                Arguments.of(Position.of("b1"), Direction.EAST, PieceType.ROOK,
                        new Route(Arrays.asList(
                                Position.of("c1"),
                                Position.of("d1"),
                                Position.of("e1"),
                                Position.of("f1"),
                                Position.of("g1"),
                                Position.of("h1")))),
                Arguments.of(Position.of("b1"), Direction.NNE, PieceType.KNIGHT,
                        new Route(Arrays.asList(
                                Position.of("c3"),
                                Position.of("d5"),
                                Position.of("e7"))))
        );
    }

    @Test
    void contains() {
    }

    @Test
    void remove() {
    }
}