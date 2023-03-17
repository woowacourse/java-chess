package techcourse.fp.movingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static chess.domain.PositionFixtures.A1;
import static chess.domain.PositionFixtures.A3;
import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.B2;
import static chess.domain.PositionFixtures.B8;
import static chess.domain.PositionFixtures.C1;
import static chess.domain.PositionFixtures.C3;
import static chess.domain.PositionFixtures.D2;
import static chess.domain.PositionFixtures.D4;
import static chess.domain.PositionFixtures.H8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import chess.movingStrategy.MoveRightDown;
import chess.movingStrategy.MovingStrategy;

class RightMoveDownTest {

    private final MovingStrategy rightDown = new MoveRightDown();

    @Nested
    public class MovableTest {

        @DisplayName("대각선 우하향으로 이동하면 true를 반환한다.")
        @Test
        void sucess_movable() {
            assertThat(rightDown.movable(B2, C1)).isTrue();
        }

        @DisplayName("대각선 우상향으로 이동하면 false 반환한다.")
        @Test
        void fail_by_right_up() {
            assertThat(rightDown.movable(B2, D4)).isFalse();
        }

        @DisplayName("대각선 좌상향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_left_up() {
            assertThat(rightDown.movable(B2, A3)).isFalse();
        }

        @DisplayName("대각선 좌하향으로 이동하면 false를 반환한다.")
        @Test
        void fail_by_left_down() {
            assertThat(rightDown.movable(B2, A1)).isFalse();
        }

        @DisplayName("File이 같으면 false를 반환한다.")
        @Test
        void fail_by_same_file() {
            assertThat(rightDown.movable(B8, B1)).isFalse();
        }

        @DisplayName("Rank가 같으면 false를 반환한다.")
        @Test
        void fail_by_same_rank() {
            assertThat(rightDown.movable(B8, H8)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 우하향으로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(rightDown.move(C3)).isEqualTo(D2);
        }
    }
}
