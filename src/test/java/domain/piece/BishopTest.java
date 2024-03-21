package domain.piece;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("비숍의 움직임 방향을 가져온다.")
    @Test
    void getBishopDirection() {
        Coordinate coordinate = new Coordinate(new Row(0), new Column(3));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(2));

        Bishop bishop = new Bishop(Color.BLACK);

        Assertions.assertThat(bishop.getDirection(coordinate, nextCoordinate, false)).isEqualTo(List.of(1, -1));
    }

}
