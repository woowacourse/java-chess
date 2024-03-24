package domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatCode;

import domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {"4/c", "6/c", "3/a"}, delimiter = '/')
    @DisplayName("퀸은 모든 방향을 이동할 수 있다.")
    void invalidMoveTest(String row, String column) {
        Coordinate coordinate = Coordinate.from("a4");
        Coordinate destination = Coordinate.from(column + row);

        int rowDiff = coordinate.calculateRowDifference(destination);
        int columnDiff = coordinate.calculateColumnDifference(destination);

        assertThatCode(() -> QueenStrategy.getMoveStrategy(rowDiff, columnDiff))
                .doesNotThrowAnyException();
    }

}
