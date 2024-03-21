package domain.piece;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸의 움직임 방향을 가져온다.")
    @Test
    void getQueenDirection() {
        Coordinate coordinate = new Coordinate(new Row(0), new Column(3));
        Coordinate nextCoordinate = new Coordinate(new Row(6), new Column(3));

        Queen queen = new Queen(Color.BLACK);

        Assertions.assertThat(queen.getDirection(coordinate, nextCoordinate, false)).isEqualTo(List.of(1, 0));
    }
}
