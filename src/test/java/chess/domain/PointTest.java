package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @Test
    @DisplayName("행과 열의 정보를 알 수 있다.")
    void createPoint() {
        Point point = new Point('a', 1);

        assertThat(point).isEqualTo(new Point('a', 1));
    }
}
