package domain.piece.strategy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/c", "4/d"}, delimiter = '/')
    @DisplayName("Knight의 이동 전략에 포함되지 않는 경우 예외를 발생시킨다.")
    void invalidMoveTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("c3");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatThrownBy(() -> KnightStrategy.getMoveStrategy(rowDiff, columnDiff))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}
