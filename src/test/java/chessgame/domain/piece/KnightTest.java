package chessgame.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KnightTest {

    @ParameterizedTest(name = "(4, 4)에서 ({0}, {1})로 이동")
    @CsvSource(value = {"6:5", "6:3", "2:5", "2:3", "5:6", "3:6", "3:2", "5:2"}, delimiter = ':')
    @DisplayName("나이트 가능한 대각선 이동 테스트")
    void isReachableByRule(int row, int col) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(4, 4);
        Coordinate endCoordinate = Coordinate.fromOnBoard(row, col);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "왼쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("나이트 불가능한 왼쪽 이동 테스트")
    void isReachableByRuleCantLeft(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 6);
        Coordinate endCoordinate = Coordinate.fromOnBoard(0, 6 - distance);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "오른쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("나이트 불가능한 오른쪽 이동 테스트")
    void isReachableByRuleCantRight(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(0, distance);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "위쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("나이트 불가능한 위로 이동 테스트")
    void isReachableByRuleCantUp(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(distance, 0);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "아래쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("나이트 가능한 아래로 이동 테스트")
    void isReachableByRuleCantDown(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(6 - distance, 0);
        Knight knight = new Knight();

        assertThat(knight.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }
}
