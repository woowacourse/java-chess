package chess.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    @DisplayName("행과 열의 정보를 알 수 있다.")
    void createPoint() {
        Point point = Point.of('a', 1);

        assertThat(point).isEqualTo(Point.of('a', 1));
    }
}
