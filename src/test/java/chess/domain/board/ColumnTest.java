package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnTest {

    @Test
    @DisplayName("대칭되는 열 반환")
    void opposingColumn() {
        assertThat(Column.B.opposingColumn()).isEqualTo(Column.G);
    }
}