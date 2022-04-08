package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    @DisplayName("Column 을 이동시킨뒤 해당 Column 을 반환하는 기능")
    void move() {
        assertThat(Column.B.move(-1)).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("Column 의 value 의 차를 구하는 기능")
    void subtractValue() {
        assertThat(Column.A.subtractValue(Column.B)).isEqualTo(1);
    }
}