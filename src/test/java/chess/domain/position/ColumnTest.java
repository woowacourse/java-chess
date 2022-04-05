package chess.domain.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {

    @Test
    void plusColumn() {
        Column col = Column.B;
        assertThat(col.plusColumn(-1)).isEqualTo(Column.A);
    }
}