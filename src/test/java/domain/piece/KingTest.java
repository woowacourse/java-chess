package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹의 움직임 방향을 가져온다.")
    @Test
    void getKingDirection() {
        Coordinate coordinate = Coordinate.from("F1");
        Coordinate nextCoordinate = Coordinate.from("E2");

        King king = new King(Color.BLACK);

        Assertions.assertThat(king.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.from(List.of(1, -1)));
    }
}
