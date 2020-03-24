package chess.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CoordinateTest {

    @DisplayName("두 좌표 값의 차이를 구한다.")
    @Test
    void calculateVariation() {
        Coordinate coordinate1 = new Coordinate(File.A, Rank.ONE);
        Coordinate coordinate2 = new Coordinate(File.B, Rank.TWO);
        Assertions.assertThat(coordinate1.calculateVariation(coordinate2))
                .isEqualTo(new Variation(1, 1));
    }
}