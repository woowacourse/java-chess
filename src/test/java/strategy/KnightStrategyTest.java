package strategy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import position.Column;
import position.Row;

class KnightStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/3", "4/4"}, delimiter = '/')
    @DisplayName("Knight의 이동 전략에 포함되지 않는 경우 예외를 발생시킨다.")
    void invalidMoveTest(int row, int column) {
        Coordinate coordinate = new Coordinate(new Row(3), new Column(3));
        Coordinate destination = new Coordinate(new Row(row), new Column(column));

        int rowDiff = coordinate.checkRow(destination);
        int columnDiff = coordinate.checkColumn(destination);

        assertThatThrownBy(() -> KnightStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
