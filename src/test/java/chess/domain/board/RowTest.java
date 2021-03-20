package chess.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RowTest {

    @Test
    @DisplayName("반대되는 행 반환")
    void opposingRow() {
        Assertions.assertThat(Row.TWO.opposingRow()).isEqualTo(Row.SEVEN);
    }
}