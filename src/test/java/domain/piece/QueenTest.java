package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸의 움직임 방향을 가져온다.")
    @Test
    void getQueenDirection() {
        Coordinate coordinate = Coordinate.from("D6");
        Coordinate nextCoordinate = Coordinate.from("C6");

        Queen queen = new Queen(Color.BLACK);

        Assertions.assertThat(queen.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.from(List.of(0, -1)));
    }
}
