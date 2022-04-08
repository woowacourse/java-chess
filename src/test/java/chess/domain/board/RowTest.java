package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {

    @Test
    @DisplayName("Row 을 이동시킨뒤 해당 Row 을 반환하는 기능")
    void move() {
        assertThat(Row.TWO.move(-1)).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("Row 의 value 의 차를 구하는 기능")
    void subtractValue() {
        assertThat(Row.ONE.subtractValue(Row.TWO)).isEqualTo(1);
    }
}