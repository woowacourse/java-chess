package chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Row.getLocation("a")).isEqualTo(0);
        assertThat(Row.getLocation("b")).isEqualTo(1);
        assertThat(Row.getLocation("c")).isEqualTo(2);
        assertThat(Row.getLocation("d")).isEqualTo(3);
        assertThat(Row.getLocation("e")).isEqualTo(4);
        assertThat(Row.getLocation("f")).isEqualTo(5);
        assertThat(Row.getLocation("g")).isEqualTo(6);
        assertThat(Row.getLocation("h")).isEqualTo(7);
        assertThat(Row.getLocation("h")).isNotEqualTo(1);
    }
}