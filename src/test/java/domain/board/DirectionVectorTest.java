package domain.board;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DirectionVectorTest {

    @Test
    @DisplayName("북쪽으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateN() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(3, 0);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(1, 0));
    }

    @Test
    @DisplayName("북동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNE() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(3, 3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(1, 1));
    }

    @Test
    @DisplayName("동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateE() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, 3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(0, 1));
    }

    @Test
    @DisplayName("남동으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSE() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(-3, 3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(-1, 1));
    }

    @Test
    @DisplayName("남으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateS() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(-3, 0);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(-1, 0));
    }

    @Test
    @DisplayName("남으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateSW() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(-3, -3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(-1, -1));
    }

    @Test
    @DisplayName("서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateW() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, -3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(0, -1));
    }

    @Test
    @DisplayName("북서으로 이동하는 경우에 방향 벡터를 찾을 수 있다")
    void calculateNW() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(-3, 3);

        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);

        assertThat(directionVector).isEqualTo(new Coordinate(-1, 1));
    }
}
