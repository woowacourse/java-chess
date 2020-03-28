package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RouteFinderTest {

    @ParameterizedTest
    @MethodSource("createPositionAndPieceType")
    void findRoute(Position fromPosition, Position toPosition, Team team, PieceType pieceType, Route expected) {
        Route foundRoute = RouteFinder.findRoute(fromPosition, toPosition, team, pieceType);

        assertThat(foundRoute).isEqualTo(expected);
    }

    private static Stream<Arguments> createPositionAndPieceType() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3"), Team.WHITE, PieceType.PAWN,
                        new RouteToProceed(new ArrayList<>())),
                Arguments.of(Position.of("a2"), Position.of("a4"), Team.WHITE, PieceType.PAWN,
                        new RouteToProceed(Arrays.asList(Position.of("a3")))),
                Arguments.of(Position.of("a2"), Position.of("b3"), Team.WHITE, PieceType.PAWN,
                        new RouteToAttack(new ArrayList<>()))
        );
    }
}