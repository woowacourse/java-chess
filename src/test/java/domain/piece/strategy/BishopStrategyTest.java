package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/3", "2/3"}, delimiter = '/')
    @DisplayName("비숍은 위, 아래로 이동할 수 없다.")
    void upDownTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(3), new Column(3));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> BishopStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"3/2", "3/4"}, delimiter = '/')
    @DisplayName("비숍은 좌우로 이동할 수 없다.")
    void leftRightTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(3), new Column(3));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> BishopStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
