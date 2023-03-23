package chess.helper.arguments;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A4;
import static chess.helper.PositionFixture.B3;
import static chess.helper.PositionFixture.B5;
import static chess.helper.PositionFixture.C3;
import static chess.helper.PositionFixture.C4;
import static chess.helper.PositionFixture.C5;
import static chess.helper.PositionFixture.D1;
import static chess.helper.PositionFixture.D3;
import static chess.helper.PositionFixture.D5;
import static chess.helper.PositionFixture.D8;
import static chess.helper.PositionFixture.E3;
import static chess.helper.PositionFixture.E4;
import static chess.helper.PositionFixture.E5;
import static chess.helper.PositionFixture.E6;
import static chess.helper.PositionFixture.F3;
import static chess.helper.PositionFixture.F5;
import static chess.helper.PositionFixture.H4;
import static chess.model.piece.Direction.EAST;
import static chess.model.piece.Direction.NORTH;
import static chess.model.piece.Direction.NORTH_EAST;
import static chess.model.piece.Direction.NORTH_EAST_EAST;
import static chess.model.piece.Direction.NORTH_NORTH_EAST;
import static chess.model.piece.Direction.NORTH_WEST;
import static chess.model.piece.Direction.NORTH_WEST_WEST;
import static chess.model.piece.Direction.SOUTH;
import static chess.model.piece.Direction.SOUTH_EAST;
import static chess.model.piece.Direction.SOUTH_EAST_EAST;
import static chess.model.piece.Direction.SOUTH_WEST;
import static chess.model.piece.Direction.SOUTH_WEST_WEST;
import static chess.model.piece.Direction.WEST;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class PositionArguments {

    private PositionArguments() {
    }

    private static Stream<Arguments> provideValidFindNextPositionArguments() {
        return Stream.of(
                Arguments.of(NORTH, D5), Arguments.of(EAST, E4),
                Arguments.of(SOUTH, D3), Arguments.of(WEST, C4),
                Arguments.of(NORTH_EAST, E5), Arguments.of(NORTH_WEST, C5),
                Arguments.of(SOUTH_EAST, E3), Arguments.of(SOUTH_WEST, C3),
                Arguments.of(NORTH_NORTH_EAST, E6), Arguments.of(NORTH_WEST_WEST, B5),
                Arguments.of(NORTH_EAST_EAST, F5), Arguments.of(SOUTH_WEST_WEST, B3),
                Arguments.of(SOUTH_EAST_EAST, F3)
        );
    }

    private static Stream<Arguments> provideInvalidFindNextPositionArguments() {
        return Stream.of(
                Arguments.of(NORTH, D8), Arguments.of(EAST, H4), Arguments.of(SOUTH, D1), Arguments.of(WEST, A1),
                Arguments.of(NORTH_EAST, D8), Arguments.of(NORTH_EAST, H4), Arguments.of(NORTH_WEST, A4),
                Arguments.of(NORTH_WEST, D8), Arguments.of(NORTH_WEST, A4), Arguments.of(NORTH_WEST, D8),
                Arguments.of(SOUTH_EAST, H4), Arguments.of(SOUTH_EAST, H4), Arguments.of(SOUTH_EAST, D1),
                Arguments.of(SOUTH_WEST, A4), Arguments.of(SOUTH_WEST, D1)
        );
    }
}
