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
        Step step = new Step(direction, LocationState.EMPTY);
        assertThat(step.isOrthogonalDirection()).isTrue();
    }


    @DisplayName("대각선인지 확인할 수 있다.")
    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP", "RIGHT", "DOWN", "LEFT"},
            mode = Mode.EXCLUDE)
    void isDiagonalTest(Direction direction) {
        Step step = new Step(direction, LocationState.EMPTY);
        assertThat(step.isDiagonalDirection()).isTrue();
    }

    @DisplayName("경로가 비었는지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, true", "ENEMY, false", "ALLY, false"})
    void isEmptyTest(LocationState state, boolean expected) {
        Step step = new Step(Direction.DOWN, state);
        assertThat(step.isEmpty()).isEqualTo(expected);
    }

    @DisplayName("경로에 적이 있는지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, false", "ENEMY, true", "ALLY, false"})
    void isEnemyTest(LocationState state, boolean expected) {
        Step step = new Step(Direction.DOWN, state);
        assertThat(step.hasEnemy()).isEqualTo(expected);
    }
}

