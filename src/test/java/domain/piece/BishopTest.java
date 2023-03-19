package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class BishopTest {

    @Test
    @DisplayName("비숍은 정상 생성이 된다")
    void constructorTest() {
        assertThatCode(Bishop::new)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍은 디폴트 상태를 가진다")
    void propertyTest() {
        Piece bishop = new Bishop();

        assertThat(bishop.canReap()).isFalse();
        assertThat(bishop.isPawn()).isFalse();
    }

    @ParameterizedTest(name = "우측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleRightUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleLeftUp(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleLeftBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 7);
        Coordinate endCoordinate = new Coordinate(7 - distance, 7 - distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "우측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 7})
    void isReachableByRuleRightBottom(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "우측으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleRight(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(0, distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "좌측으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 7);
        Coordinate endCoordinate = new Coordinate(0, 7 - distance);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "상단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleUp(int distance) {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(distance, 0);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "하단으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 7})
    void isNotReachableByRuleDown(int distance) {
        Coordinate startCoordinate = new Coordinate(7, 0);
        Coordinate endCoordinate = new Coordinate(7 - distance, 0);
        Bishop bishop = new Bishop();

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }
}
