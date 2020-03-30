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

@SuppressWarnings("NonAsciiCharacters")
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
                Arguments.of(Position.of("B1"), Direction.NORTH, PieceType.ROOK,
                        new Route(Arrays.asList(
                                Position.of("B2"),
                                Position.of("B3"),
                                Position.of("B4"),
                                Position.of("B5"),
                                Position.of("B6"),
                                Position.of("B7"),
                                Position.of("B8")))),
                Arguments.of(Position.of("B1"), Direction.EAST, PieceType.ROOK,
                        new Route(Arrays.asList(
                                Position.of("C1"),
                                Position.of("D1"),
                                Position.of("E1"),
                                Position.of("F1"),
                                Position.of("G1"),
                                Position.of("H1")))),
                Arguments.of(Position.of("B1"), Direction.NORTH_NORTH_EAST, PieceType.KNIGHT,
                        new Route(Arrays.asList(
                                Position.of("C3")))),
                Arguments.of(Position.of("B1"), Direction.NORTH, PieceType.PAWN,
                        new RouteToProceed(Arrays.asList(
                                Position.of("B2"))))
        );
    }
}