package domain.game;

import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("(1,1) -> (2,2)로 이동하면,  방향이다.")
    @Test
    void moveToNorthEast() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.SOUTH_EAST);
    }
}
