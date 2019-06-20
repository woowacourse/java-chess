package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    void Point_동등_확인() {
        Point actual = new Point(3, 3);
        Point expected = new Point(3, 3);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Point_비동등_확인() {
        Point actual = new Point(3, 3);
        Point expected = new Point(4, 3);
        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    void source_target의_x값_차의_절대값_비교() {
        Point source = new Point(3, 3);
        Point target = new Point(2, 3);

        int actual = source.calculateXsDiff(target);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void source_target의_y값_차의_절대값_비교() {
        Point source = new Point(3, 7);
        Point target = new Point(2, 3);

        int actual = source.calculateYsDiff(target);
        assertThat(actual).isEqualTo(4);
    }
}
