package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RookTest {

    @ParameterizedTest(name = "룩은 왼쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    void isReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 6);
        Coordinate endCoordinate = new Coordinate(0, 6 - distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 오른쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    void isReachableByRuleRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 위쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    void isReachableByRuleUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 아래쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    void isReachableByRuleDown(int distance) {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(6 - distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }
}
