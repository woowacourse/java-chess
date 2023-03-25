package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

    @Test
    @DisplayName("x좌표를 받아서 다음 x 좌표를 반환한다.")
    void getNextXPoint() {
        // given
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(upDirection.getNextXPoint(2)).isEqualTo(2);
    }

    @Test
    @DisplayName("y좌표를 받아서 다음 y 좌표를 반환한다.")
    void getNextYPoint() {
        // given
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(upDirection.getNextYPoint(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("수평으로 이동하는지 여부를 반환한다.")
    void isHorizontalMovable() {
        // given
        final Direction rightDirection = Direction.RIGHT;
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(rightDirection.isHorizontal()).isTrue();
        assertThat(upDirection.isHorizontal()).isFalse();
    }

    @Test
    @DisplayName("수직으로 이동하는지 여부를 반환한다.")
    void isVerticalMovable() {
        // given
        final Direction rightDirection = Direction.RIGHT;
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(rightDirection.isVertical()).isFalse();
        assertThat(upDirection.isVertical()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("generateDirectionByDifference")
    @DisplayName("x좌표, y좌표 차이에 따른 방향을 반환한다.")
    void directionOf(int xDiff, int yDiff, Direction expectedDirection) {
        // when, then
        assertThat(Direction.of(xDiff, yDiff)).isSameAs(expectedDirection);
    }

    static Stream<Arguments> generateDirectionByDifference() {
        return Stream.of(
                Arguments.of(0, 1, Direction.UP),
                Arguments.of(1, 1, Direction.UP_RIGHT),
                Arguments.of(1, 0, Direction.RIGHT),
                Arguments.of(1, -1, Direction.DOWN_RIGHT),
                Arguments.of(0, -1, Direction.DOWN),
                Arguments.of(-1, -1, Direction.DOWN_LEFT),
                Arguments.of(-1, 0, Direction.LEFT),
                Arguments.of(-1, 1, Direction.UP_LEFT),
                Arguments.of(1, 2, Direction.UP_UP_RIGHT),
                Arguments.of(-1, 2, Direction.UP_UP_LEFT),
                Arguments.of(2, 1, Direction.RIGHT_RIGHT_UP),
                Arguments.of(2, -1, Direction.RIGHT_RIGHT_DOWN),
                Arguments.of(1, -2, Direction.DOWN_DOWN_RIGHT),
                Arguments.of(-1, -2, Direction.DOWN_DOWN_LEFT),
                Arguments.of(-2, 1, Direction.LEFT_LEFT_UP),
                Arguments.of(-2, -1, Direction.LEFT_LEFT_DOWN)
        );
    }
}
