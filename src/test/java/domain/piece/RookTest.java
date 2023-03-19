package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class RookTest {

    @Test
    @DisplayName("룩은 정상 생성이 된다")
    void constructorTest() {
        assertThatCode(Rook::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩은 디폴트 상태를 가진다")
    void propertyTest() {
        Piece rook = new Rook();

        assertThat(rook.canReap()).isFalse();
        assertThat(rook.isPawn()).isFalse();
    }

    @ParameterizedTest(name = "룩은 왼쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 6);
        Coordinate endCoordinate = new Coordinate(0, 6 - distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 오른쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 위쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 아래쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleDown(int distance) {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(6 - distance, 0);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "룩은 우측 상단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleNE(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "룩은 우측 하단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleSE(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "룩은 좌측 하단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleSW(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 7);
        Coordinate endCoordinate = new Coordinate(7 - distance, 7 - distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "룩은 좌측 상단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleNW(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 7);
        Coordinate endCoordinate = new Coordinate(distance, 7 - distance);
        Rook rook = new Rook();

        assertThat(rook.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }
}
