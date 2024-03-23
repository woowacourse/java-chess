package domain.game;

import domain.position.Position;
import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("(1,1) -> (2,2)로 이동하면, 북동 방향이다.")
    @Test
    void moveToNorthEast() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.NORTH_EAST);
    }
}
