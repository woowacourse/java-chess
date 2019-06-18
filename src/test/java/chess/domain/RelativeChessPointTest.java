package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeChessPointTest {
    @Test
    void of() {
        assertThat(RelativeChessPoint.of(1, 2)).isEqualTo(RelativeChessPoint.of(1, 2));
    }

    @Test
    void toUnit_0_0() {
        assertThat(RelativeChessPoint.of(0, 0).toUnit())
                .isEqualTo(RelativeChessPoint.of(0, 0));

    }

    @Test
    void toUnit_1_0() {
        assertThat(RelativeChessPoint.of(1, 0).toUnit())
                .isEqualTo(RelativeChessPoint.of(1, 0));

    }

    @Test
    void toUnit_0_1() {
        assertThat(RelativeChessPoint.of(0, 1).toUnit())
                .isEqualTo(RelativeChessPoint.of(0, 1));

    }

    @Test
    void toUnit_2_4() {
        assertThat(RelativeChessPoint.of(2, 4).toUnit())
                .isEqualTo(RelativeChessPoint.of(1, 2));

    }

    @Test
    void toUnit_minus1_0() {
        assertThat(RelativeChessPoint.of(-1, 0).toUnit())
                .isEqualTo(RelativeChessPoint.of(-1, 0));

    }

    @Test
    void toUnit_0_minus1() {
        assertThat(RelativeChessPoint.of(0, -1).toUnit())
                .isEqualTo(RelativeChessPoint.of(0, -1));

    }

    @Test
    void toUnit_2_minus4() {
        assertThat(RelativeChessPoint.of(2, -4).toUnit())
                .isEqualTo(RelativeChessPoint.of(1, -2));

    }

    @Test
    void toUnit_minus2_minus4() {
        assertThat(RelativeChessPoint.of(-2, -4).toUnit())
                .isEqualTo(RelativeChessPoint.of(-1, -2));

    }
}