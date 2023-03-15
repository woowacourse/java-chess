package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CoordinateTest {

    @Test
    @DisplayName("동일한 행이라면 참을 반환한다")
    void isSameRow() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, 5);

        assertThat(startCoordinate.isSameRow(endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("동일한 열이라면 참을 반환한다")
    void isSameCol() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(5, 0);

        assertThat(startCoordinate.isSameCol(endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 1인 경우 참을 반환한다")
    void hasInclinationOfOne() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        assertThat(startCoordinate.hasInclinationOfOne(endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 -1인 경우 참을 반환한다")
    void hasInclinationOfMinusOne() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate endCoordinate = new Coordinate(1, 0);

        assertThat(startCoordinate.hasInclinationOfMinusOne(endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("거리가 1인 경우 참을 반환한다")
    void hasDistanceOfOne() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        assertThat(startCoordinate.hasDistanceOfOne(endCoordinate)).isTrue();
    }
}
