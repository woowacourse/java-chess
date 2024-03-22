package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class DirectionTest {
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
