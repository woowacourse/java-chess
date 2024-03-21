package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PathTest {
    @DisplayName("경로의 길이는 7칸을 넘을 수 없다.")
    @Test
    void pathMaxLengthTest() {
        assertThatThrownBy(
                () -> new Path(List.of(
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY),
                        new Step(Direction.DOWN, SquareState.EMPTY)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로의 길이는 7칸을 넘을 수 없습니다.");
    }

    @DisplayName("경로의 길이는 1칸 이상이어야 한다.")
    @Test
    void pathMinLengthTest() {
        assertThatThrownBy(() -> new Path(Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제자리 경로를 생성할 수 없습니다.");
    }

    @DisplayName("경로의 길이를 판별할 수 있다.")
    @Test
    void isSizeOfTest() {
        Path path = new Path(List.of(
                new Step(Direction.DOWN, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY)
        ));

        assertThat(path.isSizeOf(2)).isTrue();
    }

    @DisplayName("방향의 종류 개수를 판별할 수 있다.")
    @Test
    void categoryNumOfTest() {
        Path path = new Path(List.of(
                new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                new Step(Direction.DOWN, SquareState.EMPTY)
        ));

        assertThat(path.categoryNumOf(2)).isTrue();
    }

    @DisplayName("대각선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsDiagonalTest {

        @DisplayName("대각선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN, SquareState.EMPTY)
            ));

            assertThat(path.containsDiagonal()).isTrue();
        }

        @DisplayName("대각선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN, SquareState.EMPTY),
                    new Step(Direction.UP, SquareState.EMPTY)
            ));

            assertThat(path.containsDiagonal()).isFalse();
        }
    }

    @DisplayName("수직 또는 수평선을 포함하고 있는지 확인할 수 있다.")
    @Nested
    class ContainsOrthogonalTest {
        @DisplayName("수직 또는 수평선을 포함하고 있다면 참을 반환한다.")
        @Test
        void containsTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN, SquareState.EMPTY)
            ));
            assertThat(path.containsOrthogonal()).isTrue();
        }

        @DisplayName("수직 또는 수평선을 포함하고 있지 않다면 거짓을 반환한다.")
        @Test
        void notContainsTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));
            assertThat(path.containsOrthogonal()).isFalse();
        }
    }

    @DisplayName("경로에 다른 기물이 없는지 확인한다.")
    @Nested
    class IsAllEmptyTest {
        @DisplayName("경로에 다른 기물이 없으면 참을 반환한다.")
        @Test
        void emptyTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));
            assertThat(path.isAllEmpty()).isTrue();
        }

        @DisplayName("경로에 다른 기물이 있으면 거짓을 반환한다.")
        @Test
        void notEmptyTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ALLY)
            ));

            assertThat(path.isAllEmpty()).isFalse();
        }
    }

    @DisplayName("목적지에 갈 수 있는지 확인한다.")
    @Nested
    class CanReachTest {

        @DisplayName("목적지가 비어있으면 도달할 수 있다.")
        @Test
        void emptyTargetTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY)
            ));

            assertThat(path.canReach()).isTrue();
        }

        @DisplayName("목적지에 적 기물이 있으면 도달할 수 있다.")
        @Test
        void enemyTargetTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ENEMY)
            ));

            assertThat(path.canReach()).isTrue();
        }


        @DisplayName("목적지에 아군 기물이 있으면 도달할 수 없다.")
        @Test
        void allyTargetTest() {
            Path path = new Path(List.of(
                    new Step(Direction.DOWN_LEFT, SquareState.EMPTY),
                    new Step(Direction.DOWN_LEFT, SquareState.ALLY)
            ));

            assertThat(path.canReach()).isFalse();
        }
    }
}
