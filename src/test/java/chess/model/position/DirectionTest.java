package chess.model.position;

import static chess.model.Fixtures.A1;
import static chess.model.Fixtures.B1;
import static chess.model.Fixtures.B2;
import static chess.model.Fixtures.B3;
import static chess.model.Fixtures.B4;
import static chess.model.Fixtures.C4;
import static chess.model.Fixtures.C5;
import static chess.model.Fixtures.E3;
import static chess.model.Fixtures.H1;
import static chess.model.position.Direction.DOWN_DOWN_LEFT;
import static chess.model.position.Direction.DOWN_RIGHT;
import static chess.model.position.Direction.NONE;
import static chess.model.position.Direction.RIGHT;
import static chess.model.position.Direction.UP;
import static chess.model.position.Direction.UP_UP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

    @DisplayName("출발 위치와 도착 위치의 델타 값으로 방향을 찾는다")
    @ParameterizedTest
    @MethodSource("provideCoordinateAndDeltaDirection")
    void findDirectionByDelta(Position source, Position target, Direction expected) {
        Direction direction = Direction.findDirectionByDelta(source, target);
        assertThat(direction).isEqualTo(expected);
    }

    public static Stream<Arguments> provideCoordinateAndDeltaDirection() {
        return Stream.of(
            Arguments.of(B2, B4, UP_UP),
            Arguments.of(C5, B3, DOWN_DOWN_LEFT),
            Arguments.of(A1, B1, RIGHT),
            Arguments.of(B1, B4, NONE)
        );
    }

    @DisplayName("출발 위치와 도착 위치의 방향을 찾는다")
    @ParameterizedTest
    @MethodSource("provideCoordinateAndDirection")
    void findDirection(Position source, Position target, Direction expected) {
        Direction direction = Direction.findDirection(source, target);
        assertThat(direction).isEqualTo(expected);
    }

    public static Stream<Arguments> provideCoordinateAndDirection() {
        return Stream.of(
            Arguments.of(B2, B4, UP),
            Arguments.of(C5, E3, DOWN_RIGHT),
            Arguments.of(A1, H1, RIGHT),
            Arguments.of(B1, C4, NONE)
        );
    }
}
