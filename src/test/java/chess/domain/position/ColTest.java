package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Col.getLocation("a")).isEqualTo(0);
        assertThat(Col.getLocation("b")).isEqualTo(1);
        assertThat(Col.getLocation("c")).isEqualTo(2);
        assertThat(Col.getLocation("d")).isEqualTo(3);
        assertThat(Col.getLocation("e")).isEqualTo(4);
        assertThat(Col.getLocation("f")).isEqualTo(5);
        assertThat(Col.getLocation("g")).isEqualTo(6);
        assertThat(Col.getLocation("h")).isEqualTo(7);
        assertThat(Col.getLocation("h")).isNotEqualTo(1);
    }
}