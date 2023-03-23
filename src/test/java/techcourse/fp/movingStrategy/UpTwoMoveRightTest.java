package techcourse.fp.movingStrategy;

import chess.domain.movingStrategy.MoveUpTwoRight;
import chess.domain.movingStrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UpTwoMoveRightTest {

    private final MovingStrategy upTwoRight = MoveUpTwoRight.get();

    @Nested
    public class MovableTest {

        @DisplayName("위로 2칸, 오른쪽으로 한 칸 움직인다면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(upTwoRight.movable(C2, D4)).isTrue();
        }

        @DisplayName("상하좌우 방향은 false를 반환한다.")
        @Test
        void fail() {
            assertAll(
                    () -> assertThat(upTwoRight.movable(C2, C3)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, C1)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, B2)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, A2)).isFalse()
            );
        }

        @DisplayName("대각선 방향은 false를 반환한다.")
        @Test
        void diagnol_fail() {
            assertAll(
                    () -> assertThat(upTwoRight.movable(C2, D3)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, D1)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, B1)).isFalse(),
                    () -> assertThat(upTwoRight.movable(C2, B3)).isFalse());
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 위로 2칸, 오른쪽으로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(upTwoRight.move(C2)).isEqualTo(D4);
        }
    }
}
