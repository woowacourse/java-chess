package domain.game;

import static fixture.PositionFixture.F1;
import static fixture.PositionFixture.b1;
import static fixture.PositionFixture.b2;
import static fixture.PositionFixture.c3;
import static fixture.PositionFixture.c4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("(b,1) -> (b,2)로 이동하면, 북동 방향이다.")
    @Test
    void moveToNorthEast() {
        Direction findDirection = Direction.findDirection(b1(), b2());
        Assertions.assertThat(findDirection).isEqualTo(Direction.NORTH_EAST);
    }

    @DisplayName("(b,1) -> (c, 3)로 이동하면, UP_RIGHT 방향이다.")
    @Test
    void moveToUpRight() {
        Direction findDirection = Direction.findDirection(b1(), c3());
        Assertions.assertThat(findDirection).isEqualTo(Direction.UP_RIGHT);
    }

    @DisplayName("(c,4) -> (f,1)로 이동하면, 남동 방향이다.")
    @Test
    void moveToSouthEast() {
        Direction findDirection = Direction.findDirection(c4(), F1());
        Assertions.assertThat(findDirection).isEqualTo(Direction.SOUTH_EAST);
    }
}
