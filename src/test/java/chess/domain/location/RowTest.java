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

class RowTest {

    @DisplayName("Row의 거리를 계산한다.")
    @Test
    void calculateDistanceTest() {
        Row source = Row.SIX;
        Row target = Row.ONE;
        assertThat(source.calculateDistance(target)).isEqualTo(-5);
    }

    @DisplayName("Row를 잘 생성한다.")
    @Nested
    class ConstructTest {
        @DisplayName("1~8 사이의 숫자를 이용해 객체를 생성할 수 있다.")
        @ParameterizedTest
        @ValueSource(strings = {"1", "8"})
        void constructTest(String input) {
            assertThatCode(() -> Row.of(input))
                    .doesNotThrowAnyException();
        }

        @DisplayName("범위 이외의 숫자를 이용해 객체를 생성하면 예외가 발생한다.")
        @Test
        void outOfBoundConstructTest() {
            assertThatThrownBy(() -> Row.of("9"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 Row 입력입니다.");
        }
    }

    @DisplayName("주어진 방향에 따라 이동한다.")
    @Nested
    class moveTest {
        @DisplayName("주어진 방향이 위쪽 방향이므로 상단으로 이동한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP", "UP_RIGHT", "UP_LEFT"})
        void moveUpsideTest(Direction direction) {
            Row row = Row.of("2");
            assertThat(row.move(direction)).isEqualTo(Row.of("3"));
        }

        @DisplayName("주어진 방향이 아래쪽 방향이므로 하단으로 이동한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"DOWN", "DOWN_RIGHT", "DOWN_LEFT"})
        void moveDownsideTest(Direction direction) {
            Row row = Row.of("2");
            assertThat(row.move(direction)).isEqualTo(Row.of("1"));
        }

        @DisplayName("주어진 위쪽 방향으로 이동이 불가하면 예외가 발생한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"UP", "UP_RIGHT", "UP_LEFT"})
        void moveUpsideExceptionTest(Direction direction) {
            Row row = Row.of("8");
            assertThatThrownBy(() -> row.move(direction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 방향 입력입니다.");
        }

        @DisplayName("주어진 아래쪽 방향으로 이동이 불가하면 예외가 발생한다.")
        @ParameterizedTest
        @EnumSource(value = Direction.class, names = {"DOWN", "DOWN_RIGHT", "DOWN_LEFT"})
        void moveDownsideExceptionTest(Direction direction) {
            Row row = Row.of("1");
            assertThatThrownBy(() -> row.move(direction))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("잘못된 방향 입력입니다.");
        }
    }
}
