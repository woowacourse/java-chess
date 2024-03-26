package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class StepTest {

    @DisplayName("수평 또는 수직선인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "RIGHT", "DOWN", "LEFT"})
    void isOrthogonalTest(Direction direction) {
        Step step = new Step(direction, SquareState.EMPTY);
        assertThat(step.isOrthogonal()).isTrue();
    }


    @DisplayName("대각선인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP", "RIGHT", "DOWN", "LEFT"},
            mode = Mode.EXCLUDE)
    void isDiagonalTest(Direction direction) {
        Step step = new Step(direction, SquareState.EMPTY);
        assertThat(step.isDiagonal()).isTrue();
    }

    @DisplayName("경로가 비었는지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, true", "ENEMY, false", "ALLY, false"})
    void isEmptyTest(SquareState state, boolean expected) {
        Step step = new Step(Direction.DOWN, state);
        assertThat(step.isEmpty()).isEqualTo(expected);
    }

    @DisplayName("경로에 적이 있는지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, false", "ENEMY, true", "ALLY, false"})
    void isEnemyTest(SquareState state, boolean expected) {
        Step step = new Step(Direction.DOWN, state);
        assertThat(step.isEnemy()).isEqualTo(expected);
    }

    @DisplayName("경로가 위쪽 방향인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "UP_RIGHT", "UP_LEFT"})
    void isUpsideTest(Direction direction) {
        Step step = new Step(direction, SquareState.EMPTY);
        assertThat(step.isUpside()).isTrue();
    }

    @DisplayName("경로가 아래 방향인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"DOWN", "DOWN_RIGHT", "DOWN_LEFT"})
    void isDownsideTest(Direction direction) {
        Step step = new Step(direction, SquareState.EMPTY);
        assertThat(step.isDownside()).isTrue();
    }
}

