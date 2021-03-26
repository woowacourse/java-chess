package chess.domain.position;

import chess.domain.util.RowConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RowConverterTest {
    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(RowConverter.getLocation("1")).isEqualTo(7);
        assertThat(RowConverter.getLocation("2")).isEqualTo(6);
        assertThat(RowConverter.getLocation("3")).isEqualTo(5);
        assertThat(RowConverter.getLocation("4")).isEqualTo(4);
        assertThat(RowConverter.getLocation("5")).isEqualTo(3);
        assertThat(RowConverter.getLocation("6")).isEqualTo(2);
        assertThat(RowConverter.getLocation("7")).isEqualTo(1);
        assertThat(RowConverter.getLocation("8")).isEqualTo(0);
        assertThat(RowConverter.getLocation("8")).isNotEqualTo(1);
    }
}