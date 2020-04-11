package chess.domain.coordinate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CoordinateTest {

    @DisplayName("두 좌표 값의 차이를 구한다.")
    @Test
    void calculateVariation() {
        Coordinate source = Coordinate.of(File.A, Rank.ONE);
        Coordinate target = Coordinate.of(File.B, Rank.THREE);
        Assertions.assertThat(target.calculateVector(source))
                .isEqualTo(new Vector(1, 2));
    }
}