package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnTest {

    @Test
    @DisplayName("value 를 빼주는 기능")
    void subtract() {
        assertThat(Column.A.subtract(Column.B)).isEqualTo(1);
    }
}