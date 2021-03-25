package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Row.location("1")).isEqualTo(7);
        assertThat(Row.location("2")).isEqualTo(6);
        assertThat(Row.location("3")).isEqualTo(5);
        assertThat(Row.location("4")).isEqualTo(4);
        assertThat(Row.location("5")).isEqualTo(3);
        assertThat(Row.location("6")).isEqualTo(2);
        assertThat(Row.location("7")).isEqualTo(1);
        assertThat(Row.location("8")).isEqualTo(0);
        assertThat(Row.location("8")).isNotEqualTo(1);
    }
}