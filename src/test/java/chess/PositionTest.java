package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("포지션 좌표를 Enum 으로 받아 생성한다.")
    void constructor_2() {
        assertThat(new Position(Column.A, Row.ONE)).isInstanceOf(Position.class);
    }
}
