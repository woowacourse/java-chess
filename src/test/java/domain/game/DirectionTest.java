package domain.game;

import domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("(1,1) -> (2,2)로 이동하면,  방향이다.")
    @Test
    void moveToNorthEast() {
        Position sourcePosition = PositionFixture.generateSourcePosition();
        Position targetPosition = PositionFixture.generateTargetPosition();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.SOUTH_EAST);
    }
}
