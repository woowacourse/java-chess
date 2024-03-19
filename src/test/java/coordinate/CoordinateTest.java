package coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import column.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import row.Row;

class CoordinateTest {

    @DisplayName("입력 받은 죄표로 이동한다.")
    @Test
    void move() {
        Column column = new Column(0);
        Row row = new Row(0);

        Coordinate coordinate = new Coordinate(row, column);
        coordinate.move(1, 1);

        assertThat(coordinate).isEqualTo(new Coordinate(new Row(1), new Column(1)));
    }
}
