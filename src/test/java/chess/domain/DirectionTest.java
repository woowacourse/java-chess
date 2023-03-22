package chess.domain;

import static chess.domain.math.Direction.DOWN;
import static chess.domain.math.Direction.DOWN_LEFT;
import static chess.domain.math.Direction.DOWN_RIGHT;
import static chess.domain.math.Direction.LEFT;
import static chess.domain.math.Direction.RIGHT;
import static chess.domain.math.Direction.UP;
import static chess.domain.math.Direction.UP_LEFT;
import static chess.domain.math.Direction.UP_RIGHT;
import static chess.domain.math.Direction.findDirection;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    void findDirection_up() {
        var current = new Position(Rank.FIVE, File.H);
        var target = new Position(Rank.SIX, File.H);

        assertThat(findDirection(current, target)).isEqualTo(UP);
    }

    @Test
    void findDirection_down() {
        var current = new Position(Rank.SIX, File.H);
        var target = new Position(Rank.FIVE, File.H);

        assertThat(findDirection(current, target)).isEqualTo(DOWN);
    }

    @Test
    void findDirection_left() {
        var current = new Position(Rank.SIX, File.B);
        var target = new Position(Rank.SIX, File.A);

        assertThat(findDirection(current, target)).isEqualTo(LEFT);
    }

    @Test
    void findDirection_right() {
        var current = new Position(Rank.SIX, File.A);
        var target = new Position(Rank.SIX, File.B);

        assertThat(findDirection(current, target)).isEqualTo(RIGHT);
    }

    @Test
    void findDirection_upLeft() {
        var current = new Position(Rank.FIVE, File.B);
        var target = new Position(Rank.SIX, File.A);

        assertThat(findDirection(current, target)).isEqualTo(UP_LEFT);
    }

    @Test
    void findDirection_upRight() {
        var current = new Position(Rank.FIVE, File.A);
        var target = new Position(Rank.SIX, File.B);

        assertThat(findDirection(current, target)).isEqualTo(UP_RIGHT);
    }

    @Test
    void findDirection_downLeft() {
        var current = new Position(Rank.SIX, File.B);
        var target = new Position(Rank.FIVE, File.A);

        assertThat(findDirection(current, target)).isEqualTo(DOWN_LEFT);
    }

    @Test
    void findDirection_downRight() {
        var current = new Position(Rank.SIX, File.A);
        var target = new Position(Rank.FIVE, File.B);


        assertThat(findDirection(current, target)).isEqualTo(DOWN_RIGHT);
    }
}
