package chess.domain.chesspoint;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeChessPointTest {
    @Test
    void of() {
        assertThat(RelativeChessPoint.of(1, 2)).isEqualTo(RelativeChessPoint.of(1, 2));
    }

    @Test
    void of_캐시여부() {
        assertThat(RelativeChessPoint.of(-1, 1) == RelativeChessPoint.of(-1, 1)).isTrue();
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

    @Test
    void calculateUnitDirection_위로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 1);

        assertThat(RelativeChessPoint.calculateUnitDirection(sourcePoint, targetPoint)).isEqualTo(RelativeChessPoint.of(1, 0));
    }

    @Test
    void calculateUnitDirection_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 3);

        assertThat(RelativeChessPoint.calculateUnitDirection(sourcePoint, targetPoint)).isEqualTo(RelativeChessPoint.of(0, 1));
    }
}