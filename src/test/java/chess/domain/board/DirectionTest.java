package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class DirectionTest {
    @DisplayName("수평 또는 수직선인지 확인할 수 있다.")
    @Nested
    class IsOrthogonalTest {
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP", "RIGHT", "DOWN", "LEFT"})
        void trueTest(Direction direction) {
            assertThat(direction.isOrthogonal()).isTrue();
        }

        @ParameterizedTest
        @EnumSource(value = Direction.class,
                names = {"UP", "RIGHT", "DOWN", "LEFT"},
                mode = Mode.EXCLUDE)
        void falseTest(Direction direction) {
            assertThat(direction.isOrthogonal()).isFalse();
        }
    }

    @DisplayName("대각선인지 확인할 수 있다.")
    @Nested
    class IsDiagonalTest {
        @ParameterizedTest
        @EnumSource(value = Direction.class,
                names = {"UP", "RIGHT", "DOWN", "LEFT"},
                mode = Mode.EXCLUDE)
        void trueTest(Direction direction) {
            assertThat(direction.isDiagonal()).isTrue();
        }

        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP", "RIGHT", "DOWN", "LEFT"})
        void falseTest(Direction direction) {
            assertThat(direction.isDiagonal()).isFalse();
        }
    }

    @DisplayName("위쪽 방향인지 확인할 수 있다.")
    @Nested
    class IsUpsideTest {
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP", "UP_LEFT", "UP_RIGHT"})
        void trueTest(Direction direction) {
            assertThat(direction.isUpside()).isTrue();
        }

        @ParameterizedTest
        @EnumSource(value = Direction.class,
                names = {"UP", "UP_LEFT", "UP_RIGHT"},
                mode = Mode.EXCLUDE)
        void falseTest(Direction direction) {
            assertThat(direction.isUpside()).isFalse();
        }

        @DisplayName("아래쪽 방향인지 확인할 수 있다.")
        @Nested
        class IsDownsideTest {
            @ParameterizedTest
            @EnumSource(value = Direction.class, names = {"DOWN", "DOWN_LEFT", "DOWN_RIGHT"})
            void trueTest(Direction direction) {
                assertThat(direction.isDownside()).isTrue();
            }

            @ParameterizedTest
            @EnumSource(value = Direction.class,
                    names = {"DOWN", "DOWN_LEFT", "DOWN_RIGHT"},
                    mode = Mode.EXCLUDE)
            void falseTest(Direction direction) {
                assertThat(direction.isDownside()).isFalse();
            }
        }

        @DisplayName("오른쪽 방향인지 확인할 수 있다.")
        @Nested
        class IsRightSideTest {
            @ParameterizedTest
            @EnumSource(value = Direction.class, names = {"RIGHT", "UP_RIGHT", "DOWN_RIGHT"})
            void trueTest(Direction direction) {
                assertThat(direction.isRightSide()).isTrue();
            }

            @ParameterizedTest
            @EnumSource(value = Direction.class,
                    names = {"RIGHT", "UP_RIGHT", "DOWN_RIGHT"},
                    mode = Mode.EXCLUDE)
            void falseTest(Direction direction) {
                assertThat(direction.isRightSide()).isFalse();
            }
        }

        @DisplayName("왼쪽 방향인지 확인할 수 있다.")
        @Nested
        class IsLeftSideTest {
            @ParameterizedTest
            @EnumSource(value = Direction.class, names = {"LEFT", "UP_LEFT", "DOWN_LEFT"})
            void trueTest(Direction direction) {
                assertThat(direction.isLeftSide()).isTrue();
            }

            @ParameterizedTest
            @EnumSource(value = Direction.class,
                    names = {"LEFT", "UP_LEFT", "DOWN_LEFT"},
                    mode = Mode.EXCLUDE)
            void falseTest(Direction direction) {
                assertThat(direction.isLeftSide()).isFalse();
            }
        }
    }
}
