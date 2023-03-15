package techcourse.fp.movingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B3;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.C3;
import static techcourse.fp.chess.domain.PositionFixtures.D1;
import static techcourse.fp.chess.domain.PositionFixtures.D3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LeftTwoUpStrategyTest {

    private final MovingStrategy leftTwoUp = LeftTwoUpStrategy.create();

    @Nested
    public class MovableTest {

        @DisplayName("좌로 2칸, 위로 1칸 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(leftTwoUp.movable(C3, A4)).isTrue();
        }

        @DisplayName("상하좌우 방향은 false를 반환한다.")
        @Test
        void fail() {
            assertAll(
                    () -> assertThat(leftTwoUp.movable(C2, C3)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, C1)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, B2)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, A2)).isFalse()
            );
        }

        @DisplayName("대각선 방향은 false를 반환한다.")
        @Test
        void diagnol_fail() {
            assertAll(
                    () -> assertThat(leftTwoUp.movable(C2, D3)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, D1)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, B1)).isFalse(),
                    () -> assertThat(leftTwoUp.movable(C2, B3)).isFalse());
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 좌로 2칸, 위로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(leftTwoUp.move(C3)).isEqualTo(A4);
        }
    }
}
