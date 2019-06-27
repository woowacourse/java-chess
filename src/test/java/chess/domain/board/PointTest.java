package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
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

    @Test
    void calculateGradient_InfiniteValue() {
        assertThat(Point.of(0, 0).calculateGradient(Point.of(0, 1))).isEqualTo(Double.MAX_VALUE);
    }

    @Test
    void calculateGradient() {
        assertThat(Point.of(0, 0).calculateGradient(Point.of(1, 1))).isEqualTo(1);
    }
}