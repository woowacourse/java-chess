package techcourse.fp.movingStrategy;


import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.fp.chess.domain.PositionFixtures.B1;
import static techcourse.fp.chess.domain.PositionFixtures.B2;
import static techcourse.fp.chess.domain.PositionFixtures.B7;
import static techcourse.fp.chess.domain.PositionFixtures.B8;
import static techcourse.fp.chess.domain.PositionFixtures.H2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DownStrategyTest {


    private final MovingStrategy downStrategy = new DownStrategy();

    @Nested
    public class MovableTest {
        @DisplayName("File이 같고, target의 Rank가 더 작다면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(downStrategy.movable(B2, B1)).isTrue();
        }

        @DisplayName("File이 다르다면 false를 반환한다.")
        @Test
        void fail_movable() {
            assertThat(downStrategy.movable(B2, H2)).isFalse();
        }

        @DisplayName("File이 같지만 target의 Rank가 크다면 false를 반환한다.")
        @Test
        void fail_movable2() {
            assertThat(downStrategy.movable(B1, B8)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 한 칸 아래로 움직인다.")
        @Test
        void move() {
            assertThat(downStrategy.move(B8)).isEqualTo(B7);
        }
    }
}

