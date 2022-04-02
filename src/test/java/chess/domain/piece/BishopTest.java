package chess.domain.piece;

import static chess.domain.piece.Team.WHITE;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @DisplayName("비숍 움직이는 방향을 확인한다")
    @MethodSource
    void isMovableDirection(Direction direction, boolean result) {
        Bishop bishop = new Bishop(WHITE);
        Assertions.assertThat(bishop.isMovableDirection(direction)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDirection() {
        return Stream.of(
                Arguments.arguments(Direction.U, false),
                Arguments.arguments(Direction.D, false),
                Arguments.arguments(Direction.R, false),
                Arguments.arguments(Direction.L, false),
                Arguments.arguments(Direction.UR, true),
                Arguments.arguments(Direction.UL, true),
                Arguments.arguments(Direction.DR, true),
                Arguments.arguments(Direction.DL, true),
                Arguments.arguments(Direction.UUR, false),
                Arguments.arguments(Direction.ULL, false),
                Arguments.arguments(Direction.DDR, false),
                Arguments.arguments(Direction.DLL, false)
        );
    }

    @ParameterizedTest
    @DisplayName("비숍 움직이는 거리를 확인한다")
    @MethodSource
    void isMovableDistance(LocationDiff locationDiff, boolean result) {
        Bishop bishop = new Bishop(WHITE);
        Assertions.assertThat(bishop.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDistance() {
        return Stream.of(
                Arguments.arguments(new LocationDiff(1, 1), true),
                Arguments.arguments(new LocationDiff(2, 1), true),
                Arguments.arguments(new LocationDiff(1, 2), true),
                Arguments.arguments(new LocationDiff(2, 2), true),
                Arguments.arguments(new LocationDiff(-1, 7), true)
        );
    }
}
