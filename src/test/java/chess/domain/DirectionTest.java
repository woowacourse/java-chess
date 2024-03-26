package chess.domain;

import chess.domain.position.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DirectionTest {

    static Stream<Arguments> findDirectionArguments() {
        return Stream.of(
                Arguments.arguments(0, 1, Direction.UP),
                Arguments.arguments(0, -1, Direction.DOWN),
                Arguments.arguments(-1, 0, Direction.LEFT),
                Arguments.arguments(1, 0, Direction.RIGHT),
                Arguments.arguments(1, 1, Direction.UP_RIGHT),
                Arguments.arguments(-1, 1, Direction.UP_LEFT),
                Arguments.arguments(1, -1, Direction.DOWN_RIGHT),
                Arguments.arguments(-1, -1, Direction.DOWN_LEFT)
        );
    }

    @DisplayName("주어진 file 차이와 rank 차이를 이용하여 방향을 찾는다.")
    @ParameterizedTest
    @MethodSource("findDirectionArguments")
    void findDirection(int fileDiff, int rankDiff, Direction expected) {
        //when
        Direction direction = Direction.findDirection(fileDiff, rankDiff);

        //then
        assertThat(direction).isEqualTo(expected);
    }

    @DisplayName("주어진 fileDiff와 rankDiff로 방향 정보를 찾을 수 없을 경우, 예외를 발생한다.")
    @Test
    void findWrongDirection() {
        // when & then
        assertThatThrownBy(() -> Direction.findDirection(-2, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 방향입니다.");
    }
}
