package chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Row.getLocation("1")).isEqualTo(7);
        assertThat(Row.getLocation("2")).isEqualTo(6);
        assertThat(Row.getLocation("3")).isEqualTo(5);
        assertThat(Row.getLocation("4")).isEqualTo(4);
        assertThat(Row.getLocation("5")).isEqualTo(3);
        assertThat(Row.getLocation("6")).isEqualTo(2);
        assertThat(Row.getLocation("7")).isEqualTo(1);
        assertThat(Row.getLocation("8")).isEqualTo(0);
        assertThat(Row.getLocation("8")).isNotEqualTo(1);
    }
}