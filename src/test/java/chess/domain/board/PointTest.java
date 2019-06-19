package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class PointTest {
    @Test
    void create_LessThan0_ThrownIllegalArgument() {
        assertThatThrownBy(() -> Point.of(-1, 5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create_OverThan7_ThrownIllegalArgument() {
        assertThatThrownBy(() -> Point.of(8, 4)).isInstanceOf(IllegalArgumentException.class);
    }
}
