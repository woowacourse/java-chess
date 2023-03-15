package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    @DisplayName("킹은 오른쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleRight() {
        Position startPosition = new Position(0, 0);
        Position destination = new Position(0, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startPosition, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 왼쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleLeft() {
        Position startPosition = new Position(0, 1);
        Position destination = new Position(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startPosition, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 위쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleUp() {
        Position startPosition = new Position(0, 0);
        Position destination = new Position(1, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startPosition, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 아래쪽으로 한 칸 움직일 수 있다")
    void isReachableByRuleDown() {
        Position startPosition = new Position(1, 0);
        Position destination = new Position(0, 0);
        King king = new King();

        assertThat(king.isReachableByRule(startPosition, destination))
                .isTrue();
    }

    @Test
    @DisplayName("킹은 대각선으로 한 칸 움직일 수 있다")
    void isReachableByRule() {
        Position startPosition = new Position(1, 1);
        King king = new King();

        assertThat(king.isReachableByRule(startPosition, new Position(0, 0))).isTrue();
        assertThat(king.isReachableByRule(startPosition, new Position(0, 2))).isTrue();
        assertThat(king.isReachableByRule(startPosition, new Position(2, 0))).isTrue();
        assertThat(king.isReachableByRule(startPosition, new Position(2, 2))).isTrue();
    }
}
