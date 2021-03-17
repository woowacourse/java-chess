package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("좌표 방향 계산 테스트")
    @Test
    void calculateDirection() {
        Position coordinateSource = Position.of("b2");
        Position coordinateTarget = Position.of("c3");
        Direction direction = coordinateSource.calculateDirection(coordinateTarget);

        assertThat(direction).isEqualTo(Direction.NORTHEAST);
    }

}