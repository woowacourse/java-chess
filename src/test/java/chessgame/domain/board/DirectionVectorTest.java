package chessgame.domain.board;

import chessgame.domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirectionVectorTest {

    @Test
    @DisplayName("북쪽으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateN() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(3, 0);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.N);
    }

    @Test
    @DisplayName("북동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNE() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(3, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.NE);
    }

    @Test
    @DisplayName("동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateE() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(0, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.E);
    }

    @Test
    @DisplayName("남동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSE() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(-3, 3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SE);
    }

    @Test
    @DisplayName("남으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateS() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(-3, 0);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.S);
    }

    @Test
    @DisplayName("남서로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSW() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(-3, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SW);
    }

    @Test
    @DisplayName("서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateW() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(0, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.W);
    }

    @Test
    @DisplayName("북서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNW() {
        Coordinate startCoordinate = Coordinate.from(0, 0);
        Coordinate endCoordinate = Coordinate.from(3, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.NW);
    }

    @ParameterizedTest(name = "{0}은 (1, 1)에서 ({1}, {2})로 이동한다")
    @MethodSource("moveToDirectionTestData")
    @DisplayName("특정 위치에서 DirectionVector 방향으로 한 칸 이동할 수 있다.")
    void moveToDirection(DirectionVector directionVector, Coordinate expectCoordinate) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(1, 1);

        Coordinate resultCoordinate = directionVector.moveToDirection(startCoordinate);

        assertThat(resultCoordinate).isEqualTo(expectCoordinate);
    }

    static Stream<Arguments> moveToDirectionTestData() {
        return Stream.of(
                Arguments.arguments(DirectionVector.N, Coordinate.fromOnBoard(2, 1)),
                Arguments.arguments(DirectionVector.NE, Coordinate.fromOnBoard(2, 2)),
                Arguments.arguments(DirectionVector.E, Coordinate.fromOnBoard(1, 2)),
                Arguments.arguments(DirectionVector.SE, Coordinate.fromOnBoard(0, 2)),
                Arguments.arguments(DirectionVector.S, Coordinate.fromOnBoard(0, 1)),
                Arguments.arguments(DirectionVector.SW, Coordinate.fromOnBoard(0, 0)),
                Arguments.arguments(DirectionVector.W, Coordinate.fromOnBoard(1, 0)),
                Arguments.arguments(DirectionVector.NW, Coordinate.fromOnBoard(2, 0))
        );
    }
}
