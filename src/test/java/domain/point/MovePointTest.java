package domain.point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MovePointTest {

    @Test
    void getFromRowIndex() {
        MovePoint movePoint = new MovePoint(Point.of("b2"), Point.of("b3"));
        assertThat(movePoint.getFromRowIndex()).isEqualTo(2);
    }

    @Test
    void getFromColumnIndex() {
        MovePoint movePoint = new MovePoint(Point.of("c2"), Point.of("b3"));
        assertThat(movePoint.getFromColumnIndex()).isEqualTo(3);
    }

    @Test
    void canMovePoint() {
        MovePoint movePoint = new MovePoint(Point.of("a1"), Point.of("c3"));
        assertThat(movePoint.canMovePoint(2, 2)).isTrue();
    }
}
