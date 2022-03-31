package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DistanceTest {

    @Test
    @DisplayName("거리가 수직거리이다.")
    void isVerticalMovement() {
        Distance distance = new Distance(Position.valueOf("a7"), Position.valueOf("a1"));

        assertThat(distance.isVerticalMovement()).isTrue();
    }

    @Test
    @DisplayName("거리가 수평거리이다.")
    void isHorizontalMovement() {
        Distance distance = new Distance(Position.valueOf("a7"), Position.valueOf("e7"));
        assertThat(distance.isHorizontalMovement()).isTrue();
    }

    @Test
    @DisplayName("거리가 양의 기울기를 가진 대각선이다.")
    void isPositiveDiagonal() {
        Distance distance = new Distance(Position.valueOf("d4"), Position.valueOf("f6"));
        assertThat(distance.isPositiveDiagonal()).isTrue();
    }

    @Test
    @DisplayName("거리가 음의 기울기를 가진 대각선이다.")
    void isNegativeDiagonal() {
        Distance distance = new Distance(Position.valueOf("d4"), Position.valueOf("f2"));
        assertThat(distance.isNegativeDiagonal()).isTrue();
    }
}