package chess.domain.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Col.getLocation("1")).isEqualTo(7);
        assertThat(Col.getLocation("2")).isEqualTo(6);
        assertThat(Col.getLocation("3")).isEqualTo(5);
        assertThat(Col.getLocation("4")).isEqualTo(4);
        assertThat(Col.getLocation("5")).isEqualTo(3);
        assertThat(Col.getLocation("6")).isEqualTo(2);
        assertThat(Col.getLocation("7")).isEqualTo(1);
        assertThat(Col.getLocation("8")).isEqualTo(0);
        assertThat(Col.getLocation("8")).isNotEqualTo(1);
    }
}