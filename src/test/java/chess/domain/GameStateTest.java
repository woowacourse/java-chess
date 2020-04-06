package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameStateTest {
    @ParameterizedTest
    @CsvSource(value = {"no", "pause"})
    @DisplayName("이외의 입력이 들어왔을 때 ERROR 를 반환")
    void otherInputTest(String input) {
        assertThat(GameState.of(input)).isEqualTo(GameState.ERROR);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력값이 존재하지 않을 때 예외처리")
    void nullTest(String input) {
        assertThatThrownBy(() -> GameState.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
