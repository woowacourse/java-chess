package strategy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import position.Column;
import position.Row;

class RookStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"2/2", "2/4", "4/2", "4/4"}, delimiter = '/')
    @DisplayName("룩은 대각선으로 이동할 수 없다.")
    void rookInvalidMoveTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(3), new Column(3));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.checkRow(destination);
        int columnDiff = coordinate.checkColumn(destination);
        
        assertThatThrownBy(() -> RookStrategy.getMovingStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid move");
    }
}
