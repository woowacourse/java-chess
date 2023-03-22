package domain.piece.nonsliding;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class KingTest {

    @Test
    @DisplayName("킹은 기본 상태를 가진다")
    void propertyTest() {
        King king = new King(Color.WHITE);

        assertThat(king.canJump()).isFalse();
        assertThat(king.isPawn()).isFalse();
        assertThat(king.isKing()).isTrue();
        assertThat(king.getPoint()).isEqualTo(0);
        assertThat(king.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("킹은 오른쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleRight() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate destination = new Coordinate(0, 1);
        King king = new King(Color.WHITE);

        assertThat(king.isMovable(startCoordinate, destination, Situation.NEUTRAL))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleLeft() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate destination = new Coordinate(0, 0);
        King king = new King(Color.WHITE);

        assertThat(king.isMovable(startCoordinate, destination, Situation.NEUTRAL))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 위쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate destination = new Coordinate(1, 0);
        King king = new King(Color.WHITE);

        assertThat(king.isMovable(startCoordinate, destination, Situation.NEUTRAL))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 아래쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate destination = new Coordinate(0, 0);
        King king = new King(Color.WHITE);

        assertThat(king.isMovable(startCoordinate, destination, Situation.NEUTRAL))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 대각선으로 한 칸 움직일 수 있다")
    void isReachableByRule() {
        Coordinate startCoordinate = new Coordinate(1, 1);
        King king = new King(Color.WHITE);

        assertThat(king.isMovable(startCoordinate, new Coordinate(0, 0), Situation.NEUTRAL)).isTrue();
        assertThat(king.isMovable(startCoordinate, new Coordinate(0, 2), Situation.NEUTRAL)).isTrue();
        assertThat(king.isMovable(startCoordinate, new Coordinate(2, 0), Situation.NEUTRAL)).isTrue();
        assertThat(king.isMovable(startCoordinate, new Coordinate(2, 2), Situation.NEUTRAL)).isTrue();
    }
}
