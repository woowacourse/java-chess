package domain.piece;

import domain.piece.point.File;
import domain.piece.point.Point;
import domain.piece.point.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DirectionTest {
    private static Stream<Arguments> maskingParam() {
        return Stream.of(
                arguments(Direction.UP, new Point(File.A, Rank.ONE), new Point(File.A, Rank.TWO)),
                arguments(Direction.UP_RIGHT, new Point(File.F, Rank.TWO), new Point(File.G, Rank.THREE)),
                arguments(Direction.RIGHT, new Point(File.D, Rank.THREE), new Point(File.E, Rank.THREE)),
                arguments(Direction.DOWN_RIGHT, new Point(File.C, Rank.SIX), new Point(File.D, Rank.FIVE)),
                arguments(Direction.DOWN, new Point(File.F, Rank.SEVEN), new Point(File.F, Rank.SIX)),
                arguments(Direction.DOWN_LEFT, new Point(File.C, Rank.THREE), new Point(File.B, Rank.TWO)),
                arguments(Direction.LEFT, new Point(File.D, Rank.SEVEN), new Point(File.C, Rank.SEVEN)),
                arguments(Direction.UP_LEFT, new Point(File.C, Rank.THREE), new Point(File.B, Rank.FOUR))
        );
    }

    @ParameterizedTest(name = "{0} 방향이면 {1} 포인트가 {2} 로 이동한다.")
    @MethodSource("maskingParam")
    @DisplayName("방향에 따라 이동한다.")
    void move_direction(Direction direction, Point actualPoint, Point expectedPoint) {
        assertThat(direction.move(actualPoint)).isEqualTo(expectedPoint);
    }

}
