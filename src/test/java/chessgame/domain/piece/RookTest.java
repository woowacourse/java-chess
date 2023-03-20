package chessgame.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RookTest {

    @ParameterizedTest(name = "왼쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("룩 가능한 왼쪽 이동 테스트")
    void isReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 6);
        Coordinate endCoordinate = new Coordinate(0, 6 - distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "오른쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("룩 가능한 오른쪽 이동 테스트")
    void isReachableByRuleRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "위쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("룩 가능한 위쪽 이동 테스트")
    void isReachableByRuleUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "아래쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("룩 가능한 아래 이동 테스트")
    void isReachableByRuleDown(int distance) {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(6 - distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "(6, 3)에서 ({0}, {1})으로 이동할 수 없다")
    @CsvSource(value = {"7,2", "7,4", "5,2", "5,4"})
    @DisplayName("룩 불가능한 대각선 이동 테스트")
    void isReachableByRuleCantDiagonal(int row, int col) {
        Coordinate startCoordinate = new Coordinate(6, 3);
        Coordinate endCoordinate = new Coordinate(row, col);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }
}
