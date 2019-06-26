package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateXTest {

    @Test
    void movePositive() {
        assertThat(CoordinateX.A.move(3).get()).isEqualTo(CoordinateX.D);
    }

    @Test
    void moveNegative() {
        assertThat(CoordinateX.D.move(-2).get()).isEqualTo(CoordinateX.B);
    }
}
