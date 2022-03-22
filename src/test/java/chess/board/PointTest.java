package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    @DisplayName("올바른 값이 들어왔을 떄 정상적으로 Point 를 만드는지 체크한다.")
    void createPointTest() {
        int x = 3;
        int y = 4;

        assertThat(Point.of(x, y)).isNotNull();
    }

    @Test
    @DisplayName("값에 의해 비교할 수 있다.")
    void testEqualsByValue() {
        Point aPoint = Point.of(3, 3);
        Point bPoint = Point.of(3, 3);

        assertThat(aPoint).isEqualTo(bPoint);
    }
}
