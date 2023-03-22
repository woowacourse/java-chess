package chessgame.domain.piecetype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CoordinateTest {

    @Test
    @DisplayName("상대 좌표와의 기울기가 1인 경우를 계산한다")
    void hasInclinationOfOne() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(1, 1);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(Inclination.ONE);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 -1인 경우를 계산한다")
    void hasInclinationOfMinusOne() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 1);
        Coordinate endCoordinate = Coordinate.fromOnBoard(1, 0);

        assertThat(startCoordinate.getInclination(endCoordinate)).isEqualTo(Inclination.MINUS_ONE);
    }

    @Test
    @DisplayName("거리가 1인 경우 참을 반환한다")
    void hasDistanceOfOne() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(1, 1);

        assertThat(startCoordinate.hasDistanceLessThan(endCoordinate, 1)).isTrue();
    }

    @Test
    @DisplayName("두 좌표 차이가 양수인 경우에도 작동한다")
    void minusWithAbsoluteValuePositive() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(1, 1);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(Coordinate.fromOnBoard(1, 1));
    }

    @Test
    @DisplayName("두 좌표 차이가 음수인 경우에도 작동한다")
    void minusWithAbsoluteValueNegative() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(1, 1);
        Coordinate endCoordinate = Coordinate.fromOnBoard(0, 0);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(Coordinate.fromOnBoard(1, 1));
    }
}
