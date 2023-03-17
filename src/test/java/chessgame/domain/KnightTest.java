package chessgame.domain;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.piece.Knight;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KnightTest {

    @ParameterizedTest(name = "(4, 4)에서 ({0}, {1})로의 이동이 가능하다")
    @CsvSource(value = {"6:5", "6:3", "2:5", "2:3", "5:6", "3:6", "3:2", "5:2"}, delimiter = ':')
    void isReachableByRule(int row, int col) {
        Coordinate startCoordinate = new Coordinate(4, 4);
        Coordinate endCoordinate = new Coordinate(row, col);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }
}
