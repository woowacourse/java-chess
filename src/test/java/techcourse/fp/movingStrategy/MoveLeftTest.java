package techcourse.fp.movingStrategy;

import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A8;
import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.B2;
import static chess.domain.PositionFixtures.B8;
import static chess.domain.PositionFixtures.H1;
import static org.assertj.core.api.Assertions.assertThat;

class MoveLeftTest {

    private final MovingStrategy leftStrategy = MoveLeft.instance();

    @Nested
    public class MovableTest {
        @DisplayName("Rank가 같고, target의 File이 더 작다면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(leftStrategy.movable(H1, A1)).isTrue();
        }

        @DisplayName("Rank가 다르다면 false를 반환한다.")
        @Test
        void fail_movable() {
            assertThat(leftStrategy.movable(B2, B8)).isFalse();
        }

        @DisplayName("Rank가 같고, target의 File이 더 크다면 false를 반환한다.")
        @Test
        void fail_movable2() {
            assertThat(leftStrategy.movable(A8, B8)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 한 칸 왼쪽으로 움직인다.")
        @Test
        void move() {
            assertThat(leftStrategy.move(B1)).isEqualTo(A1);
        }
    }
}
