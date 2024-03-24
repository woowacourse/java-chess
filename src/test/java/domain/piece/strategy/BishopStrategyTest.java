package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/c", "2/c"}, delimiter = '/')
    @DisplayName("비숍은 위, 아래로 이동할 수 없다.")
    void upDownTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("c3");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> BishopStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"3/c", "3/d"}, delimiter = '/')
    @DisplayName("비숍은 좌우로 이동할 수 없다.")
    void leftRightTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("c3");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> BishopStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
