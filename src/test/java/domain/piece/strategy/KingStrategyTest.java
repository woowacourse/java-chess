package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"0/2", "2/4", "0/0"}, delimiter = '/')
    @DisplayName("킹은 한 칸만 이동할 수 있다.")
    void invalidMoveTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(2), new Column(2));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> KingStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
