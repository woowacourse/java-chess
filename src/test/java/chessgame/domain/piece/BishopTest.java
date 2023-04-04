package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;
import chessgame.domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BishopTest {

    @ParameterizedTest(name = "우측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 가능한 우측 상단 이동 테스트")
    void isReachableByRuleRightUp(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(distance, distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 가능한 좌측 상단 이동 테스트")
    void isReachableByRuleLeftUp(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(7, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(7 - distance, distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 가능한 좌측 하단 이동 테스트")
    void isReachableByRuleLeftBottom(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(7, 7);
        Coordinate endCoordinate = Coordinate.createOnBoard(7 - distance, 7 - distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "우측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 가능한 우측 하단 이동 테스트")
    void isReachableByRuleRightBottom(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(7, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(7 - distance, distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }



    @ParameterizedTest(name = "왼쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 불가능한 왼쪽 이동 테스트")
    void isReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 6);
        Coordinate endCoordinate = Coordinate.createOnBoard(0, 6 - distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "오른쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 불가능한 오른쪽 이동 테스트")
    void isReachableByRuleRight(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(0, distance);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "위쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 불가능한 위쪽 이동 테스트")
    void isReachableByRuleUp(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(distance, 0);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "아래쪽으로 {0}칸 이동할 수 없다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("비숍 불가능한 아래 이동 테스트")
    void isReachableByRuleDown(int distance) {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(6 - distance, 0);
        Bishop bishop = new Bishop(Camp.WHITE);

        assertThat(bishop.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }
}
