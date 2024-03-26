package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareStateTest {
    @DisplayName("해당 상태가 비어있는 상태인지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, true", "ENEMY, false", "ALLY, false"})
    void isEmptyTest(SquareState state, boolean expected) {
        assertThat(state.isEmpty()).isEqualTo(expected);
    }

    @DisplayName("해당 상태가 적이 있는 상태인지 확인할 수 있다.")
    @ParameterizedTest
    @CsvSource({"EMPTY, false", "ENEMY, true", "ALLY, false"})
    void isEnemyTest(SquareState state, boolean expected) {
        assertThat(state.isEnemy()).isEqualTo(expected);
    }
}
