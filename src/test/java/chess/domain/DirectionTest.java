package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DirectionTest {

    @ParameterizedTest
    @MethodSource("directions")
    void calculateDirection(int fileDistance, int rankDistance, Direction direction) {
        assertThat(Direction.of(fileDistance, rankDistance)).isEqualTo(direction);
    }

    static Stream<Arguments> directions() {
        return Stream.of(
                Arguments.of(1, 0, Direction.EAST),
                Arguments.of(-1, 0, Direction.WEST),
                Arguments.of(0, 1, Direction.NORTH),
                Arguments.of(0, -1, Direction.SOUTH),
                Arguments.of(1, 1, Direction.NORTH_EAST),
                Arguments.of(-1, 1, Direction.NORTH_WEST),
                Arguments.of(1, -1, Direction.SOUTH_EAST),
                Arguments.of(-1, -1, Direction.SOUTH_WEST),
                Arguments.of(2, 1, Direction.KNIGHT_EAST_LEFT),
                Arguments.of(2, -1, Direction.KNIGHT_EAST_RIGHT),
                Arguments.of(-2, 1, Direction.KNIGHT_WEST_LEFT),
                Arguments.of(-2, -1, Direction.KNIGHT_WEST_RIGHT),
                Arguments.of(-1, 2, Direction.KNIGHT_NORTH_LEFT),
                Arguments.of(1, 2, Direction.KNIGHT_NORTH_RIGHT),
                Arguments.of(1, -2, Direction.KNIGHT_SOUTH_LEFT),
                Arguments.of(-1, -2, Direction.KNIGHT_SOUTH_RIGHT)
        );
    }

    @Test
    @DisplayName("이동할 수 없는 방향일 경우 예외를 발생시킨다.")
    void exceptionNoDirection() {
        assertThatThrownBy(() -> Direction.of(3, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 방향입니다.");
    }
}
