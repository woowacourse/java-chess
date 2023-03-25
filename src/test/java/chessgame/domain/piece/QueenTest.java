package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.chessgame.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QueenTest {

    @ParameterizedTest(name = "우측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 우측 상단 이동 테스트")
    void isReachableByRuleRightUp(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(distance, distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 상단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 좌측 상단 이동 테스트")
    void isReachableByRuleLeftUp(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(7, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(7 - distance, distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "좌측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 좌측 하단 이동 테스트")
    void isReachableByRuleLeftBottom(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(7, 7);
        Coordinate endCoordinate = Coordinate.fromOnBoard(7 - distance, 7 - distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "우측 하단으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 우측 하단 이동 테스트")
    void isReachableByRuleRightBottom(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(7, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(7 - distance, distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "왼쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 왼쪽 이동 테스트")
    void isReachableByRuleLeft(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 6);
        Coordinate endCoordinate = Coordinate.fromOnBoard(0, 6 - distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "오른쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 오른쪽 이동 테스트")
    void isReachableByRuleRight(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(0, distance);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "위쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 위로 이동 테스트")
    void isReachableByRuleUp(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(distance, 0);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "아래쪽으로 {0}칸 이동할 수 있다")
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("퀸 가능한 아래 이동 테스트")
    void isReachableByRuleDown(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.fromOnBoard(6 - distance, 0);
        Queen queen = new Queen(Camp.WHITE);

        assertThat(queen.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }
}
