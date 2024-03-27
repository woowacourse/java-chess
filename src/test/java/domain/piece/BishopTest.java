package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍의 방향을 가져올 수 있다.")
    @Test
    void getBishopDirection() {
        Coordinate coordinate = Coordinate.from("a4");
        Coordinate nextCoordinate = Coordinate.from("b3");

        Bishop bishop = new Bishop(Color.BLACK);

        Assertions.assertThat(bishop.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.DOWN_RIGHT_DIAGONAL);
    }
}
