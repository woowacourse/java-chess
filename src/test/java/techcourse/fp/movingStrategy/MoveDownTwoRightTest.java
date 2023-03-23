package techcourse.fp.movingStrategy;

import chess.domain.movingStrategy.MoveDownTwoRight;
import chess.domain.movingStrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.A2;
import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.B2;
import static chess.domain.PositionFixtures.B3;
import static chess.domain.PositionFixtures.C1;
import static chess.domain.PositionFixtures.C2;
import static chess.domain.PositionFixtures.C3;
import static chess.domain.PositionFixtures.D1;
import static chess.domain.PositionFixtures.D3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MoveDownTwoRightTest {

    private final MovingStrategy downTwoRight = MoveDownTwoRight.get();

    @Nested
    public class MovableTest {

        @DisplayName("아래로 2칸, 우로 1칸 이동하면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(downTwoRight.movable(C3, D1)).isTrue();
        }

        @DisplayName("상하좌우 방향은 false를 반환한다.")
        @Test
        void fail() {
            assertAll(
                    () -> assertThat(downTwoRight.movable(C2, C3)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, C1)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, B2)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, A2)).isFalse()
            );
        }

        @DisplayName("대각선 방향은 false를 반환한다.")
        @Test
        void diagnol_fail() {
            assertAll(
                    () -> assertThat(downTwoRight.movable(C2, D3)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, D1)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, B1)).isFalse(),
                    () -> assertThat(downTwoRight.movable(C2, B3)).isFalse());
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 아래로 2칸, 우로 한 칸 움직인다.")
        @Test
        void move() {
            assertThat(downTwoRight.move(C3)).isEqualTo(D1);
        }
    }
}
