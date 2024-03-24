package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.KnightDirection;
import domain.direction.StraightDirection;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoordinateTest {


    @DisplayName("행 간의 차이를 계산한다.")
    @Test
    void calculateRowDifference() {
        Coordinate coordinate = new Coordinate(1, 1);
        Coordinate coordinate1 = new Coordinate(7, 3);

        assertThat(coordinate.calculateRowDifference(coordinate1)).isEqualTo(6);
    }

    @DisplayName("열 간의 차이를 계산한다.")
    @Test
    void calculateColumnDifference() {
        Coordinate coordinate = new Coordinate(1, 1);
        Coordinate coordinate1 = new Coordinate(7, 3);

        assertThat(coordinate.calculateColumnDifference(coordinate1)).isEqualTo(2);
    }

    @DisplayName("같은 행에 있는지 확인한다.")
    @Test
    void hasSameRowPosition() {
        Coordinate coordinate = new Coordinate(7, 1);
        Position row = Position.of(7);

        assertTrue(coordinate.hasSameRowPosition(row));
    }

    @Nested
    @DisplayName("입력받은 방향으로 한 칸 이동한다.")
    class MoveOneStepTest {

        Coordinate coordinate = new Coordinate(2, 2);

        @ParameterizedTest
        @MethodSource("provideDirectionArguments")
        @DisplayName("해당하는 방향으로 한 칸 이동한다.")
        void moveOneStep(Direction direction, Coordinate expected) {
            assertThat(coordinate.moveOneStep(direction)).isEqualTo(expected);
        }

        private static Stream<Arguments> provideDirectionArguments() {
            return Stream.of(
                    arguments(StraightDirection.UP, new Coordinate(1, 2)),
                    arguments(StraightDirection.DOWN, new Coordinate(3, 2)),
                    arguments(StraightDirection.LEFT, new Coordinate(2, 1)),
                    arguments(StraightDirection.RIGHT, new Coordinate(2, 3)),
                    arguments(DiagonalDirection.UP_LEFT, new Coordinate(1, 1)),
                    arguments(DiagonalDirection.UP_RIGHT, new Coordinate(1, 3)),
                    arguments(DiagonalDirection.DOWN_LEFT, new Coordinate(3, 1)),
                    arguments(DiagonalDirection.DOWN_RIGHT, new Coordinate(3, 3)),
                    arguments(KnightDirection.UP_LEFT, new Coordinate(0, 1)),
                    arguments(KnightDirection.UP_RIGHT, new Coordinate(0, 3)),
                    arguments(KnightDirection.DOWN_LEFT, new Coordinate(4, 1)),
                    arguments(KnightDirection.DOWN_RIGHT, new Coordinate(4, 3))
            );
        }
    }
}
