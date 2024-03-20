package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class DirectionTest {

    @DisplayName("경로를 생성한다.")
    @Nested
    class DirectionsTest {
        @DisplayName("제자리 경로는 생성할 수 없다.")
        @Test
        void notMoveCreateExceptionTest() {
            assertThatCode(() -> Direction.createDirections(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("제자리 경로를 생성할 수 없습니다.");
        }

        //TODO 테스트 설명 고민해보기
        @DisplayName("오른쪽위, 오른쪽, 오른쪽 경로를 생성할 수 있다.")
        @Test
        void createTest1() {
            List<Direction> directions = Direction.createDirections(1, 3);
            assertThat(directions).containsExactly(
                    Direction.UP_RIGHT, Direction.RIGHT, Direction.RIGHT
            );
        }

        //TODO 테스트 설명 고민해보기
        @DisplayName("아래, 아래, 아래 경로를 생성할 수 있다.")
        @Test
        void createTest2() {
            List<Direction> directions = Direction.createDirections(-3, 0);
            assertThat(directions).containsExactly(
                    Direction.DOWN, Direction.DOWN, Direction.DOWN
            );
        }
    }

    @DisplayName("수평 또는 수직선인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "RIGHT", "DOWN", "LEFT"})
    void isOrthogonalTest(Direction direction) {

        assertThat(direction.isOrthogonal()).isTrue();
    }

    @DisplayName("대각선인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP", "RIGHT", "DOWN", "LEFT"},
            mode = Mode.EXCLUDE)
    void isDiagonalTest(Direction direction) {
        assertThat(direction.isDiagonal()).isTrue();
    }
}
