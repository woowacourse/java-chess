package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointTest {
    @Test
    void 생성_테스트() {
        Point point = Point.get(1, 3);
        assertThat(Point.get(1, 3)).isEqualTo(point);
    }

    @Test
    void transport_테스트() {
        Point point = Point.get(1, 3);
        Direction direction = new Direction(1, 1);
        assertThat(point.transport(direction)).isEqualTo(Point.get(2, 4));
    }

    @Test
    void transport_테스트2() {
        Point point = Point.get(1, 3);
        Direction direction = new Direction(7, 7);
        assertThrows(IllegalArgumentException.class, () -> point.transport(direction));
    }

}
