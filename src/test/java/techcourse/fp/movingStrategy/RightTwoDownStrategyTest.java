package techcourse.fp.movingStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B3;
import static techcourse.fp.chess.domain.PositionFixtures.C1;
import static techcourse.fp.chess.domain.PositionFixtures.C2;
import static techcourse.fp.chess.domain.PositionFixtures.C3;
import static techcourse.fp.chess.domain.PositionFixtures.D1;
import static techcourse.fp.chess.domain.PositionFixtures.D3;
import static techcourse.fp.chess.domain.PositionFixtures.E1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.movingStrategy.MovingStrategy;
import techcourse.fp.chess.movingStrategy.RightTwoDownStrategy;

class RightTwoDownStrategyTest {

    private final MovingStrategy rightTwoDown = RightTwoDownStrategy.create();

    @Nested
    public class MovableTest {

        @DisplayName("우로 2칸, 아래로 1칸 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(rightTwoDown.movable(C2, E1)).isTrue();
        }

        @DisplayName("상하좌우 방향은 false를 반환한다.")
        @Test
        void fail() {
            assertAll(
                    () -> assertThat(rightTwoDown.movable(C2, C3)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, C1)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, B2)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, A2)).isFalse()
            );
        }

        @DisplayName("대각선 방향은 false를 반환한다.")
        @Test
        void diagnol_fail() {
            assertAll(
                    () -> assertThat(rightTwoDown.movable(C2, D3)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, D1)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, B1)).isFalse(),
                    () -> assertThat(rightTwoDown.movable(C2, B3)).isFalse());
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 우로 2칸, 아래로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(rightTwoDown.move(C2)).isEqualTo(E1);
        }
    }
}
