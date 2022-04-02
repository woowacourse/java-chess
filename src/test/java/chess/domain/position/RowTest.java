package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {
    @Test
    @DisplayName("랭크는 8가지로 이루어져 있다.")
    void countRanks() {
        assertThat(Row.values().length).isEqualTo(8);
    }

    @Test
    @DisplayName("3가 입력되면 File.THREE을 반환한다")
    void find() {
        assertThat(Row.find('3')).isEqualTo(Row.THREE);
    }
}
