package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(Col.location("a")).isEqualTo(0);
        assertThat(Col.location("b")).isEqualTo(1);
        assertThat(Col.location("c")).isEqualTo(2);
        assertThat(Col.location("d")).isEqualTo(3);
        assertThat(Col.location("e")).isEqualTo(4);
        assertThat(Col.location("f")).isEqualTo(5);
        assertThat(Col.location("g")).isEqualTo(6);
        assertThat(Col.location("h")).isEqualTo(7);
        assertThat(Col.location("h")).isNotEqualTo(1);
    }
}