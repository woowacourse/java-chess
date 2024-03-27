package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍의 움직임 방향을 가져온다.")
    @Test
    void getBishopDirection() {
        Coordinate coordinate = Coordinate.from("a4");
        Coordinate nextCoordinate = Coordinate.from("b3");

        Bishop bishop = new Bishop(Color.BLACK);

        Assertions.assertThat(bishop.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.from(List.of(-1, 1)));
    }

}
