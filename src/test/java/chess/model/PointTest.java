package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    void Point_동등_확인() {
        Point actual = new Point(XPosition.valueOf(3), YPosition.valueOf(3));
        Point expected = new Point(XPosition.valueOf(3), YPosition.valueOf(3));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void Point_비동등_확인() {
        Point actual = new Point(XPosition.valueOf(3), YPosition.valueOf(3));
        Point expected = new Point(XPosition.valueOf(4), YPosition.valueOf(3));
        assertThat(actual).isNotEqualTo(expected);
    }

}
