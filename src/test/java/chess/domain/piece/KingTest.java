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

class KingTest {

    @ParameterizedTest
    @DisplayName("킹이 움직이는 방향을 확인한다")
    @MethodSource
    void isMovableDirection(Direction direction, boolean result) {
        King king = new King(WHITE);
        Assertions.assertThat(king.isMovableDirection(direction)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDirection() {
        return Stream.of(
                Arguments.arguments(Direction.U, true),
                Arguments.arguments(Direction.D, true),
                Arguments.arguments(Direction.R, true),
                Arguments.arguments(Direction.L, true),
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
    @DisplayName("킹이 움직이는 거리를 확인한다")
    @MethodSource
    void isMovableDistance(LocationDiff locationDiff, boolean result) {
        King king = new King(WHITE);
        Assertions.assertThat(king.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> isMovableDistance() {
        return Stream.of(
                Arguments.arguments(new LocationDiff(1, 1), true),
                Arguments.arguments(new LocationDiff(2, 1), true),
                Arguments.arguments(new LocationDiff(1, 2), true),
                Arguments.arguments(new LocationDiff(2, 2), false),
                Arguments.arguments(new LocationDiff(-2, 2), false)
        );
    }
}
