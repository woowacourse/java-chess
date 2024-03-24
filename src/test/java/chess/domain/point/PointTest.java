package chess.domain.point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    @DisplayName("행과 열의 정보를 알 수 있다.")
    void createPoint() {
        Point point = Point.of(File.A, Rank.FIRST);

        assertThat(point).isEqualTo(Point.of(File.A, Rank.FIRST));
    }
}
