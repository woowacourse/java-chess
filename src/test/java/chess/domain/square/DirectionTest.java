package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest
    @MethodSource("provideDirections")
    @DisplayName("같은 방향 검증 테스트")
    void same_direction(Direction direction, int fileDifference, int rankDifference, boolean expected) {
        assertThat(direction.isSameDirection(fileDifference, rankDifference))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> provideDirections() {
        return Stream.of(
                Arguments.of(Direction.UP, 0, 1, true),
                Arguments.of(Direction.UP, 0, -1, false),
                Arguments.of(Direction.DOWN, 0, -1, true),
                Arguments.of(Direction.LEFT, -1, 0, true),
                Arguments.of(Direction.LEFT, 0, -1, false),
                Arguments.of(Direction.RIGHT, 1, 0, true),
                Arguments.of(Direction.UP_LEFT, -1, 1, true),
                Arguments.of(Direction.UP_RIGHT, 1, 1, true),
                Arguments.of(Direction.DOWN_LEFT, -1, 1, false),
                Arguments.of(Direction.DOWN_LEFT, -1, -1, true),
                Arguments.of(Direction.DOWN_RIGHT, 1, -1, true),
                Arguments.of(Direction.UP_RIGHT_RIGHT, 2, 1, true),
                Arguments.of(Direction.UP_RIGHT_RIGHT, 1, 2, false),
                Arguments.of(Direction.UP_UP_RIGHT, 2, 1, false),
                Arguments.of(Direction.UP_UP_RIGHT, 1, 2, true),
                Arguments.of(Direction.UP_LEFT_LEFT, -2, 1, true),
                Arguments.of(Direction.UP_UP_LEFT, -1, 2, true),
                Arguments.of(Direction.UP_UP_LEFT, -1, -2, false),
                Arguments.of(Direction.DOWN_LEFT_LEFT, -2, -1, true),
                Arguments.of(Direction.DOWN_DOWN_LEFT, -1, -2, true),
                Arguments.of(Direction.DOWN_DOWN_LEFT, -2, -1, false),
                Arguments.of(Direction.DOWN_RIGHT_RIGHT, 2, -1, true),
                Arguments.of(Direction.DOWN_DOWN_RIGHT, 1, -2, true)
        );
    }
}