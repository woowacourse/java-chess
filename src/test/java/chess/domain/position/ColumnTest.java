package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {
    @Test
    @DisplayName("파일는 8가지로 이루어져 있다")
    void countRanks() {
        assertThat(Column.values().length).isEqualTo(8);
    }

    @Test
    @DisplayName("e가 입력되면 File.E을 반환한다")
    void find() {
        assertThat(Column.find('e')).isEqualTo(Column.E);
    }
}
