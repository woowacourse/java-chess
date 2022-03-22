package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("Position 좌표를 입력받아 생성한다.")
    void constructor() {
        assertThat(new Position("a", "1")).isInstanceOf(Position.class);
    }
}
