package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("나이트의 방향을 가져올 수 있다.")
    @Test
    void getKnightDirection() {
        Coordinate coordinate = Coordinate.from("b1");
        Coordinate nextCoordinate = Coordinate.from("a3");

        Knight knight = new Knight(Color.BLACK);

        Assertions.assertThat(knight.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.DOWN_DOWN_LEFT_DIAGONAL);
    }
}
