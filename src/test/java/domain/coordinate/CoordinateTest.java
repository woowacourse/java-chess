package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("입력 받은 거리만큼 이동한다.")
    @Test
    void move() {
        Coordinate coordinate = Coordinate.from("a1");
        coordinate.moveByDistances(1, 1);

        assertThat(coordinate).isEqualTo(Coordinate.from("b2"));
    }
}
