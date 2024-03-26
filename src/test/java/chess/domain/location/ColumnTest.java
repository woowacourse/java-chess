package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {

    @DisplayName("Column 거리를 계산한다.")
    @Test
    void calculateDistanceTest() {
        Column source = Column.C;
        Column target = Column.G;
        assertThat(source.calculateDistance(target)).isEqualTo(4);
    }

    @DisplayName("Column을 잘 생성한다.")
    @Nested
    class ConstructTest {
        @DisplayName("A~H 사이의 알파벳을 이용해 객체를 생성할 수 있다.")
        @ParameterizedTest
        @ValueSource(strings = {"a", "A", "h", "H"})
        void constructTest(String input) {
            assertThatCode(() -> Column.of(input))
                    .doesNotThrowAnyException();
        }

        @DisplayName("범위 이외의 알파벳을 이용해 객체를 생성하면 예외가 발생한다.")
        @Test
        void outOfBoundConstructTest() {
            assertThatThrownBy(() -> Column.of("I"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 Column 입력입니다.");
        }
    }

    @DisplayName("주어진 방향에 따라 이동한다.")
    @Nested
    class moveTest {
        @DisplayName("주어진 방향이 왼쪽 방향이므로 왼쪽으로 이동한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"LEFT", "DOWN_LEFT", "UP_LEFT"})
        void moveLeftSideTest(Direction direction) {
            Column column = Column.B;
            assertThat(column.move(direction)).isEqualTo(Column.A);
        }

        @DisplayName("주어진 방향이 오른쪽 방향이므로 오른쪽으로 이동한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"RIGHT", "DOWN_RIGHT", "UP_RIGHT"})
        void moveRightSideTest(Direction direction) {
            Column column = Column.B;
            assertThat(column.move(direction)).isEqualTo(Column.C);
        }

        @DisplayName("주어진 왼쪽 방향으로 이동이 불가하면 예외가 발생한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"LEFT", "DOWN_LEFT", "UP_LEFT"})
        void moveLeftSideExceptionTest(Direction direction) {
            Column column = Column.A;
            assertThatThrownBy(() -> column.move(direction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 방향 입력입니다.");
        }

        @DisplayName("주어진 오른쪽 방향으로 이동이 불가하면 예외가 발생한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"RIGHT", "DOWN_RIGHT", "UP_RIGHT"})
        void moveRightSideExceptionTest(Direction direction) {
            Column column = Column.H;
            assertThatThrownBy(() -> column.move(direction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 방향 입력입니다.");
        }
    }
}
