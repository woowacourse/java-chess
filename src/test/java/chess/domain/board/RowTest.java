package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RowTest {

    @Test
    @DisplayName("value 를 빼주는 기능")
    void subtract() {
        assertThat(Row.ONE.subtract(Row.TWO)).isEqualTo(1);
    }
}