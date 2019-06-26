package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateYTest {
    @Test
    void movePositive() {
        assertThat(CoordinateY.RANK_2.move(2).get()).isEqualTo(CoordinateY.RANK_4);
    }

    @Test
    void moveNegative() {
        assertThat(CoordinateY.RANK_5.move(-3).get()).isEqualTo(CoordinateY.RANK_2);
    }
}
