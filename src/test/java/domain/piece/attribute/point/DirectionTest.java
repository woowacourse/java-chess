package domain.piece.attribute.point;

import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Index;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DirectionTest {
    private static Stream<Arguments> maskingParam() {
        return Stream.of(
                arguments(Direction.UP, new Index(1, 1), new Index(2, 1)),
                arguments(Direction.UP_RIGHT, new Index(2, 2), new Index(3, 3)),
                arguments(Direction.RIGHT, new Index(3, 3), new Index(3, 4)),
                arguments(Direction.DOWN_RIGHT, new Index(2, 2), new Index(1, 3)),
                arguments(Direction.DOWN, new Index(2, 2), new Index(1, 2)),
                arguments(Direction.DOWN_LEFT, new Index(2, 2), new Index(1, 1)),
                arguments(Direction.LEFT, new Index(2, 2), new Index(2, 1)),
                arguments(Direction.UP_LEFT, new Index(2, 2), new Index(3, 1))
        );
    }

    @ParameterizedTest(name = "{0} 방향이면 {1} 포인트가 {2} 로 이동한다.")
    @MethodSource("maskingParam")
    @DisplayName("방향에 따라 이동한다.")
    void move_direction(final Direction direction, final Index actualPoint, final Index expectedPoint) {
        assertThat(direction.move(actualPoint)).isEqualTo(expectedPoint);
    }

    @Test
    @DisplayName("방향이 세로 방향(위,아래) 방향이면 참을 반환 한다.")
    void true_if_direction_is_up_or_down() {

        final Set<Direction> set = EnumSet.of(Direction.UP, Direction.DOWN);
        set.forEach(direction -> assertThat(direction.isStraight()).isTrue());
    }

    @Test
    @DisplayName("그 외의 방향이면 거짓을 반환 한다.")
    void false_if_direction_otherwise() {
        final Set<Direction> set = EnumSet.allOf(Direction.class);
        set.remove(Direction.UP);
        set.remove(Direction.DOWN);

        set.forEach(direction -> assertThat(direction.isStraight()).isFalse());
    }
}
