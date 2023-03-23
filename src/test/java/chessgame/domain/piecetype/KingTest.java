package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class KingTest {

    @Test
    @DisplayName("킹은 오른쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleRight() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate destination = Coordinate.fromOnBoard(0, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isTrue();
    }

    @ParameterizedTest(name = "오른쪽으로 {0} 칸 이상 움직일 수 없다")
    @ValueSource(ints = {2, 4, 5})
    @DisplayName("킹 불가능한 오른쪽 이동 테스트")
    void isReachableByRuleCantRight(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate destination = Coordinate.fromOnBoard(0, distance);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isFalse();
    }

    @Test
    @DisplayName("왼쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleLeft() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 1);
        Coordinate destination = Coordinate.fromOnBoard(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isTrue();
    }

    @ParameterizedTest(name = "왼쪽으로 {0} 칸 이상 움직일 수 없다")
    @ValueSource(ints = {2, 4, 5})
    @DisplayName("킹 불가능한 왼쪽 이동 테스트")
    void isReachableByRuleCantLeft(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 7);
        Coordinate destination = Coordinate.fromOnBoard(0, 7 - distance);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isFalse();
    }

    @Test
    @DisplayName("킹은 위쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate destination = Coordinate.fromOnBoard(1, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isTrue();
    }

    @ParameterizedTest(name = "위쪽으로 {0} 칸 이상 움직일 수 없다")
    @ValueSource(ints = {2, 4, 5})
    @DisplayName("킹 불가능한 위로 이동 테스트")
    void isReachableByRuleCantUp(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(0, 0);
        Coordinate destination = Coordinate.fromOnBoard(distance, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isFalse();
    }

    @Test
    @DisplayName("킹은 아래쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(1, 0);
        Coordinate destination = Coordinate.fromOnBoard(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isTrue();
    }

    @ParameterizedTest(name = "아래쪽으로 {0} 칸 이상 움직일 수 없다")
    @ValueSource(ints = {2, 4, 5})
    @DisplayName("킹 불가능한 아래 이동 테스트")
    void isReachableByRuleCantDown(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(7, 0);
        Coordinate destination = Coordinate.fromOnBoard(7 - distance, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination)).isFalse();
    }

    @Test
    @DisplayName("킹은 대각선으로 한 칸 움직일 수 있다")
    void isReachableByRule() {
        Coordinate startCoordinate = Coordinate.fromOnBoard(1, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, Coordinate.fromOnBoard(0, 0))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, Coordinate.fromOnBoard(0, 2))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, Coordinate.fromOnBoard(2, 0))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, Coordinate.fromOnBoard(2, 2))).isTrue();
    }

    @ParameterizedTest(name = "대간선으로 {0} 칸 움직일 수 없다")
    @ValueSource(ints = {2, 3})
    @DisplayName("킹 불가능한 대각선 이동 테스트")
    void isReachableByRuleCantDiagonal(int distance) {
        Coordinate startCoordinate = Coordinate.fromOnBoard(4, 4);
        King king = new King();

        assertSoftly(softly -> {
            softly.assertThat(king.isReachableByRule(
                          startCoordinate, Coordinate.fromOnBoard(4 + distance, 4 + distance)))
                  .isFalse();
            softly.assertThat(king.isReachableByRule(
                          startCoordinate, Coordinate.fromOnBoard(4 + distance, 4 - distance)))
                  .isFalse();
            softly.assertThat(king.isReachableByRule(
                          startCoordinate, Coordinate.fromOnBoard(4 - distance, 4 + distance)))
                  .isFalse();
            softly.assertThat(king.isReachableByRule(
                          startCoordinate, Coordinate.fromOnBoard(4 - distance, 4 - distance)))
                  .isFalse();

        });
    }
}
