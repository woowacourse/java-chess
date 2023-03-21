package domain.piece.nonsliding;

import domain.piece.Piece;
import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KingTest {

    @Test
    @DisplayName("킹은 기본 상태를 가진다")
    void propertyTest() {
        Piece king = new King();

        assertThat(king.canJump()).isFalse();
        assertThat(king.isPawn()).isFalse();
    }

    @Test
    @DisplayName("킹은 오른쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleRight() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate destination = new Coordinate(0, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleLeft() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate destination = new Coordinate(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 위쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate destination = new Coordinate(1, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 아래쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate destination = new Coordinate(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 대각선으로 한 칸 움직일 수 있다")
    void isReachableByRule() {
        Coordinate startCoordinate = new Coordinate(1, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startCoordinate, new Coordinate(0, 0))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, new Coordinate(0, 2))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, new Coordinate(2, 0))).isTrue();
        assertThat(king.isReachableByRule(startCoordinate, new Coordinate(2, 2))).isTrue();
    }
}
