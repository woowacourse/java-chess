package techcourse.fp.movingStrategy;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class MoveLeftUpTest {

    private final MovingStrategy leftUp = MoveLeftUp.instance();

    @Nested
    public class MovableTest {

        @DisplayName("대각선 좌상향으로 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(leftUp.movable(B2, A3)).isTrue();
        }

        @DisplayName("대각선 우상향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_right_up() {
            assertThat(leftUp.movable(B2, D4)).isFalse();
        }

        @DisplayName("대각선 우하향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_right_down() {
            assertThat(leftUp.movable(B2, C1)).isFalse();
        }

        @DisplayName("대각선 좌하향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_left_down() {
            assertThat(leftUp.movable(B2, A1)).isFalse();
        }

        @DisplayName("File이 같으면 false를 반환한다.")
        @Test
        void fail_by_same_file() {
            assertThat(leftUp.movable(B8, B1)).isFalse();
        }

        @DisplayName("Rank가 같으면 false를 반환한다.")
        @Test
        void fail_by_same_rank() {
            assertThat(leftUp.movable(B8, H8)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 좌상향으로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(leftUp.move(C2)).isEqualTo(B3);
        }
    }

}
