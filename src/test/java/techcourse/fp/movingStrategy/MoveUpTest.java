package techcourse.fp.movingStrategy;

import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixtures.B1;
import static chess.domain.PositionFixtures.B2;
import static chess.domain.PositionFixtures.B8;
import static chess.domain.PositionFixtures.H2;
import static org.assertj.core.api.Assertions.assertThat;

class MoveUpTest {


    private final MovingStrategy upStrategy = MoveUp.instance();

    @Nested
    public class MovableTest {
        @DisplayName("File이 같고, target의 Rank가 더 크다면 true를 반환한다.")
        @Test
        void success_movable() {
            assertThat(upStrategy.movable(B2, B8)).isTrue();
        }

        @DisplayName("File이 다르다면 false를 반환한다.")
        @Test
        void fail_movable() {
            assertThat(upStrategy.movable(B2, H2)).isFalse();
        }

        @DisplayName("File이 같지만 target의 Rank가 작으면 false를 반환한다.")
        @Test
        void fail_movable2() {
            assertThat(upStrategy.movable(B8, B1)).isFalse();
        }
    }

    @Nested
    public class MoveTest {

        @DisplayName("move시 한 칸 위로 움직인다.")
        @Test
        void move() {
            assertThat(upStrategy.move(B1)).isEqualTo(B2);
        }
    }
}
