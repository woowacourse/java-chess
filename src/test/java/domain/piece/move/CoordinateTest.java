package domain.piece.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CoordinateTest {

    @Test
    @DisplayName("좌표는 정상 생성이 된다")
    void constructorTest() {
        Coordinate coordinate = new Coordinate(1, 2);

        assertThat(coordinate.getRow()).isEqualTo(1);
        assertThat(coordinate.getCol()).isEqualTo(2);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 1인 경우를 계산한다")
    void hasInclinationOfOne() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(1);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 -1인 경우를 계산한다")
    void hasInclinationOfMinusOne() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate endCoordinate = new Coordinate(1, 0);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(-1);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 +0인 경우를 계산한다")
    void hasInclinationOfZero() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, 1);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(0.0);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 -0인 경우를 계산한다")
    void hasInclinationOfMinusZero() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate endCoordinate = new Coordinate(0, 0);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(0.0);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 양의 무한대인 경우를 계산한다")
    void hasInclinationOfPositiveInfinity() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 0);

        System.out.println(startCoordinate.getInclination(endCoordinate));
        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 음의 무한대인 경우를 계산한다")
    void hasInclinationOfNegativeInfinity() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(0, 0);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    @DisplayName("거리가 1인 경우 참을 반환한다")
    void hasDistanceOfOne() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        assertThat(startCoordinate.hasDistanceLessThanOne(endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("두 좌표 차이가 양수인 경우에도 작동한다")
    void minusWithAbsoluteValuePositive() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(new Coordinate(1, 1));
    }

    @Test
    @DisplayName("두 좌표 차이가 음수인 경우에도 작동한다")
    void minusWithAbsoluteValueNegative() {
        Coordinate startCoordinate = new Coordinate(1, 1);
        Coordinate endCoordinate = new Coordinate(0, 0);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(new Coordinate(1, 1));
    }
}
