package domain.piece.strategy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"2/b", "2/d", "4/b", "4/d"}, delimiter = '/')
    @DisplayName("룩은 대각선으로 이동할 수 없다.")
    void rookInvalidMoveTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("c3");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> RookStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
