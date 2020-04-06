package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static chess.domain.route.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionsTest {

    @ParameterizedTest
    @MethodSource("createPieceTypeAndTeamAndDirections")
    void of(PieceType pieceType, Team team, List<Direction> directions) {
        assertThat(Directions.of(pieceType, team)).isEqualTo(directions);
    }

    private static Stream<Arguments> createPieceTypeAndTeamAndDirections() {
        return Stream.of(
                Arguments.of(PieceType.PAWN, Team.WHITE,
                        Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST)),
                Arguments.of(PieceType.PAWN, Team.BLACK,
                        Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST)),
                Arguments.of(PieceType.KNIGHT, Team.WHITE,
                        Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH)),
                Arguments.of(PieceType.KNIGHT, Team.BLACK,
                        Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH))
        );
    }
}