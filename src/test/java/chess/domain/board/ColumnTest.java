package chess.domain.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnTest {

    @Test
    @DisplayName("대칭되는 열 반환")
    void opposingColumn() {
        Assertions.assertThat(Column.B.opposingColumn()).isEqualTo(Column.G);
    }
}