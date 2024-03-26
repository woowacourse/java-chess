package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @Test
    @DisplayName("원하는 만큼 움직일 수 있는지 반환한다.")
    void canMoveMoreThenOnce() {
        Knight knight = Knight.of(Color.WHITE);

        boolean canMoveMoreThenOnce = knight.canMoveMoreThenOnce();

        Assertions.assertThat(canMoveMoreThenOnce).isFalse();
    }

    @Nested
    @DisplayName("주어진 방향으로 이동할 수 있는지 반환한다.")
    class canMoveInTargetDirection {
        @ParameterizedTest
        @CsvSource(value = {
                "KNIGHT_LEFT_UP", "KNIGHT_LEFT_DOWN",
                "KNIGHT_RIGHT_UP", "KNIGHT_RIGHT_DOWN",
                "KNIGHT_UP_LEFT", "KNIGHT_UP_RIGHT",
                "KNIGHT_DOWN_LEFT", "KNIGHT_DOWN_RIGHT"
        })
        @DisplayName("이동할 수 있는 방향이면 true를 반환한다.")
        void canMove(Direction direction) {
            Knight knight = Knight.of(Color.WHITE);

            boolean result = knight.canMoveInTargetDirection(direction);

            Assertions.assertThat(result).isTrue();
        }

        @ParameterizedTest
        @CsvSource(value = {
                "LEFT", "RIGHT", "UP", "DOWN",
                "LEFT_UP", "LEFT_DOWN",
                "RIGHT_UP", "RIGHT_DOWN"
        })
        @DisplayName("이동할 수 없는 방향이면 false를 반환한다.")
        void canNotMove(Direction direction) {
            Knight knight = Knight.of(Color.WHITE);

            boolean result = knight.canMoveInTargetDirection(direction);

            Assertions.assertThat(result).isFalse();
        }
    }

    @DisplayName("폰인지를 반환한다.")
    @Test
    void isPawn() {
        Knight knight = Knight.of(Color.WHITE);

        boolean isPawn = knight.isPawn();

        Assertions.assertThat(isPawn).isFalse();
    }
}
