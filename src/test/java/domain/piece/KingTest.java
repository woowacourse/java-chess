package domain.piece;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹의 움직임 방향을 가져온다.")
    @Test
    void getKingDirection() {
        Coordinate coordinate = new Coordinate(new Row(0), new Column(5));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(4));

        King king = new King(Color.BLACK);

        Assertions.assertThat(king.getDirection(coordinate, nextCoordinate, false)).isEqualTo(List.of(1, -1));
    }
}
