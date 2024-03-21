package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatCode;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/3", "6/3", "3/1"}, delimiter = '/')
    @DisplayName("퀸은 모든 방향을 이동할 수 있다.")
    void invalidMoveTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(4), new Column(1));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatCode(() -> QueenStrategy.getMoveStrategy(rowDiff, columnDiff))
                .doesNotThrowAnyException();
    }

}
