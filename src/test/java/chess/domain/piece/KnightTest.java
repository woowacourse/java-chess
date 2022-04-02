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

class KnightTest {

    @ParameterizedTest
    @DisplayName("나이트가 움직이는 방향을 확인한다")
    @MethodSource
    void isMovableDirection(Direction direction, boolean result) {
        Knight knight = new Knight(WHITE);
        Assertions.assertThat(knight.isMovableDirection(direction)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDirection() {
        return Stream.of(
                Arguments.arguments(Direction.U, false),
                Arguments.arguments(Direction.D, false),
                Arguments.arguments(Direction.R, false),
                Arguments.arguments(Direction.L, false),
                Arguments.arguments(Direction.UR, false),
                Arguments.arguments(Direction.UL, false),
                Arguments.arguments(Direction.DR, false),
                Arguments.arguments(Direction.DL, false),
                Arguments.arguments(Direction.UUR, true),
                Arguments.arguments(Direction.ULL, true),
                Arguments.arguments(Direction.DDR, true),
                Arguments.arguments(Direction.DLL, true)
        );
    }

    @ParameterizedTest
    @DisplayName("킹이 움직이는 거리를 확인한다")
    @MethodSource
    void isMovableDistance(LocationDiff locationDiff, boolean result) {
        Knight knight = new Knight(WHITE);
        Assertions.assertThat(knight.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDistance() {
        return Stream.of(
                Arguments.arguments(new LocationDiff(1, 2), true),
                Arguments.arguments(new LocationDiff(2, 1), true),
                Arguments.arguments(new LocationDiff(2, 4), false),
                Arguments.arguments(new LocationDiff(-2, -4), false),
                Arguments.arguments(new LocationDiff(-3, 6), false)
        );
    }
}
