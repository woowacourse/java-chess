package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class CoordinateTest {
    @Test
    void create() {
        assertThat(Coordinate.of(1)).isEqualTo(Coordinate.of(1));
    }

    @Test
    void of_LessThan0_IllegalArgumentException() {
        assertThatThrownBy(() -> Coordinate.of(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void of_OverThan7_IllegalArgumentException() {
        assertThatThrownBy(() -> Coordinate.of(8)).isInstanceOf(IllegalArgumentException.class);
    }
}
