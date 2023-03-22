package chess.domain.math;

import static chess.domain.math.Direction.DOWN;
import static chess.domain.math.Direction.DOWN_LEFT;
import static chess.domain.math.Direction.DOWN_RIGHT;
import static chess.domain.math.Direction.LEFT;
import static chess.domain.math.Direction.RIGHT;
import static chess.domain.math.Direction.UP;
import static chess.domain.math.Direction.UP_LEFT;
import static chess.domain.math.Direction.UP_RIGHT;
import static chess.domain.math.Direction.computeDirection;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void findDirection_up() {
        var current = new Position(1, 0);
        var target = new Position(0, 0);

        assertThat(computeDirection(current, target)).isEqualTo(UP);
    }

    @Test
    void findDirection_down() {
        var current = new Position(0, 0);
        var target = new Position(1, 0);

        assertThat(computeDirection(current, target)).isEqualTo(DOWN);
    }

    @Test
    void findDirection_left() {
        var current = new Position(1, 1);
        var target = new Position(1, 0);

        assertThat(computeDirection(current, target)).isEqualTo(LEFT);
    }

    @Test
    void findDirection_right() {
        var current = new Position(1, 0);
        var target = new Position(1, 1);

        assertThat(computeDirection(current, target)).isEqualTo(RIGHT);
    }

    @Test
    void findDirection_upLeft() {
        var current = new Position(1, 1);
        var target = new Position(0, 0);

        assertThat(computeDirection(current, target)).isEqualTo(UP_LEFT);
    }

    @Test
    void findDirection_upRight() {
        var current = new Position(1, 0);
        var target = new Position(0, 1);

        assertThat(computeDirection(current, target)).isEqualTo(UP_RIGHT);
    }

    @Test
    void findDirection_downLeft() {
        var current = new Position(0, 1);
        var target = new Position(1, 0);

        assertThat(computeDirection(current, target)).isEqualTo(DOWN_LEFT);
    }

    @Test
    void findDirection_downRight() {
        var current = new Position(0, 0);
        var target = new Position(1, 1);

        assertThat(computeDirection(current, target)).isEqualTo(DOWN_RIGHT);
    }
}
