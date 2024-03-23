package domain.game;

import domain.position.Position;
import fixture.PositionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("(b,1) -> (b,2)로 이동하면, 북동 방향이다.")
    @Test
    void moveToNorthEast() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC2Position();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.NORTH_EAST);
    }

    @DisplayName("(b,1) -> (c, 3)로 이동하면, UP_RIGHT 방향이다.")
    @Test
    void moveToUpRight() {
        Position sourcePosition = PositionFixture.generateB1Position();
        Position targetPosition = PositionFixture.generateC3Position();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.UP_RIGHT);
    }

    @DisplayName("(c,4) -> (f,1)로 이동하면, 남동 방향이다.")
    @Test
    void moveToSouthEast() {
        Position sourcePosition = PositionFixture.generateC4Position();
        Position targetPosition = PositionFixture.generateF1Position();

        Direction findDirection = Direction.findDirection(sourcePosition, targetPosition);
        Assertions.assertThat(findDirection).isEqualTo(Direction.SOUTH_EAST);
    }
}
