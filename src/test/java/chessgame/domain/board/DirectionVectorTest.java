package chessgame.domain.board;

import chessgame.domain.piece.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Coordinate endCoordinate = Coordinate.from(-3, -3);

        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(DirectionVector.SW);
    }
}
