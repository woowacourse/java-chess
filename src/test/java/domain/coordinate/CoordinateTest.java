package domain.coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import domain.direction.Direction;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("입력 받은 거리만큼 이동한다.")
    @Test
    void move() {
        Coordinate coordinate = Coordinate.from("a1");
        Coordinate nextCoordinate = coordinate.next(Direction.from(List.of(1, 1)));

        assertThat(nextCoordinate).isEqualTo(Coordinate.from("b2"));
    }
}
