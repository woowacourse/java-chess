package chess.domain.position;

import chess.domain.util.ColumnConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnConverterTest {

    @Test
    @DisplayName("입력받은 좌표값을 넣으면 실제 좌표값을 가져온다")
    void getLocationTest() {
        assertThat(ColumnConverter.getLocation("a")).isEqualTo(0);
        assertThat(ColumnConverter.getLocation("b")).isEqualTo(1);
        assertThat(ColumnConverter.getLocation("c")).isEqualTo(2);
        assertThat(ColumnConverter.getLocation("d")).isEqualTo(3);
        assertThat(ColumnConverter.getLocation("e")).isEqualTo(4);
        assertThat(ColumnConverter.getLocation("f")).isEqualTo(5);
        assertThat(ColumnConverter.getLocation("g")).isEqualTo(6);
        assertThat(ColumnConverter.getLocation("h")).isEqualTo(7);
        assertThat(ColumnConverter.getLocation("h")).isNotEqualTo(1);
    }
}