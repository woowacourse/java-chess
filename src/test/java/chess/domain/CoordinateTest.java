package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("좌표 방향 계산 테스트")
    @Test
    void calculateDirection() {
        Coordinate coordinateSource = new Coordinate(2, 2);
        Coordinate coordinateTarget = new Coordinate(0, 0);
        Direction direction = coordinateSource.calculateDirection(coordinateTarget);

        assertThat(direction).isEqualTo(Direction.SOUTHWEST);
    }

}