package chessgame.domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CoordinateTest {

    @Test
    @DisplayName("거리가 1인 경우 참을 반환한다")
    void hasDistanceOfOne() {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(1, 1);

        assertThat(startCoordinate.hasDistanceLessThan(endCoordinate, 1)).isTrue();
    }

    @Test
    @DisplayName("두 좌표 차이가 양수인 경우에도 작동한다")
    void minusWithAbsoluteValuePositive() {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(1, 1);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(Coordinate.createOnBoard(1, 1));
    }

    @Test
    @DisplayName("두 좌표 차이가 음수인 경우에도 작동한다")
    void minusWithAbsoluteValueNegative() {
        Coordinate startCoordinate = Coordinate.createOnBoard(1, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(0, 0);

        assertThat(startCoordinate.minusWithAbsoluteValue(endCoordinate))
                .isEqualTo(Coordinate.createOnBoard(1, 1));
    }
}
