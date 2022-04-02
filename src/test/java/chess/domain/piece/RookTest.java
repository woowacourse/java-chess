package chess.domain.piece;

import static chess.domain.piece.Team.WHITE;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @DisplayName("룩이 움직이는 방향을 확인한다")
    @MethodSource
    void isMovableDirection(Direction direction, boolean result) {
        Rook rook = new Rook(WHITE);
        Assertions.assertThat(rook.isMovableDirection(direction)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDirection() {
        return Stream.of(
                Arguments.arguments(Direction.U, true),
                Arguments.arguments(Direction.D, true),
                Arguments.arguments(Direction.R, true),
                Arguments.arguments(Direction.L, true),
                Arguments.arguments(Direction.UR, false),
                Arguments.arguments(Direction.UL, false),
                Arguments.arguments(Direction.DR, false),
                Arguments.arguments(Direction.DL, false),
                Arguments.arguments(Direction.UUR, false),
                Arguments.arguments(Direction.ULL, false),
                Arguments.arguments(Direction.DDR, false),
                Arguments.arguments(Direction.DLL, false)
        );
    }

    @ParameterizedTest
    @DisplayName("룩이 움직이는 거리를 확인한다")
    @MethodSource
    void isMovableDistance(LocationDiff locationDiff, boolean result) {
        Rook rook = new Rook(WHITE);
        Assertions.assertThat(rook.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDistance() {
        return Stream.of(
                Arguments.arguments(new LocationDiff(1, 1), true),
                Arguments.arguments(new LocationDiff(2, 1), true),
                Arguments.arguments(new LocationDiff(1, 2), true),
                Arguments.arguments(new LocationDiff(2, 2), true),
                Arguments.arguments(new LocationDiff(-2, 2), true)
        );
    }
}
