package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"1/C", "3/E", "1/A"}, delimiter = '/')
    @DisplayName("킹은 한 칸만 이동할 수 있다.")
    void invalidMoveTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("C3");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> KingStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
