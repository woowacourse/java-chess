package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class PointTest {
    @Test
    void create_LessThan1_ThrownIllegalArgument() {
        assertThatThrownBy(() -> new Point(0, 5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create_OverThan8_ThrownIllegalArgument() {
        assertThatThrownBy(() -> new Point(9, 4)).isInstanceOf(IllegalArgumentException.class);
    }

}
