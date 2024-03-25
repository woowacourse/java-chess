package domain.chess.attribute.point;

import domain.chess.Direction;
import domain.chess.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import static domain.chess.Direction.*;
import static fixture.PointFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DirectionTest {
    private static Stream<Arguments> maskingParam() {
        return Stream.of(
                arguments(UP, A1, A2),
                arguments(UP_RIGHT, A2, B3),
                arguments(RIGHT, B3, C3),
                arguments(DOWN_RIGHT, B4, C3),
                arguments(DOWN, D4, D3),
                arguments(DOWN_LEFT, F5, E4),
                arguments(LEFT, G3, F3),
                arguments(UP_LEFT, B4, A5),
                arguments(UP_UP_LEFT, C3, B5),
                arguments(UP_UP_RIGHT, D4, E6),
                arguments(RIGHT_UP_RIGHT, C3, E4),
                arguments(RIGHT_DOWN_RIGHT, F5, H4),
                arguments(DOWN_DOWN_LEFT, C3, B1),
                arguments(DOWN_DOWN_RIGHT, D5, E3),
                arguments(LEFT_UP_LEFT, C3, A4),
                arguments(LEFT_DOWN_LEFT, D5, B4)
        );
    }

    @ParameterizedTest(name = "{0} 방향이면 {1} 포인트가 {2} 로 이동한다.")
    @MethodSource("maskingParam")
    @DisplayName("방향에 따라 이동한다.")
    void move_direction(final Direction direction, final Point actualPoint, final Point expectedPoint) {
        assertThat(direction.movePoint(actualPoint)).isEqualTo(expectedPoint);
    }

    @Test
    @DisplayName("방향이 세로 방향(위,아래) 방향이면 참을 반환 한다.")
    void true_if_direction_is_up_or_down() {

        final Set<Direction> set = EnumSet.of(Direction.UP, Direction.DOWN);
        set.forEach(direction -> assertThat(direction.isVertical()).isTrue());
    }

    @Test
    @DisplayName("그 외의 방향이면 거짓을 반환 한다.")
    void false_if_direction_otherwise() {
        final Set<Direction> set = EnumSet.allOf(Direction.class);
        set.remove(Direction.UP);
        set.remove(Direction.DOWN);

        set.forEach(direction -> assertThat(direction.isVertical()).isFalse());
    }
}
