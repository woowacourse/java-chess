package chessgame.domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InclinationTest {

    @Test
    @DisplayName("상대 좌표와의 기울기가 1인 경우를 계산한다")
    void hasInclinationOfOne() {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(1, 1);

        assertThat(Inclination.of(startCoordinate, endCoordinate)).isEqualTo(Inclination.ONE);
    }

    @Test
    @DisplayName("상대 좌표와의 기울기가 -1인 경우를 계산한다")
    void hasInclinationOfMinusOne() {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(1, 0);

        assertThat(Inclination.of(startCoordinate, endCoordinate)).isEqualTo(Inclination.MINUS_ONE);
    }
}