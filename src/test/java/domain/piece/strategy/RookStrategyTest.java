package domain.piece.strategy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"2/2", "2/4", "4/2", "4/4"}, delimiter = '/')
    @DisplayName("룩은 대각선으로 이동할 수 없다.")
    void rookInvalidMoveTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(3), new Column(3));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> RookStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
