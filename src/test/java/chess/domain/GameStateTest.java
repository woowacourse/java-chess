package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class GameStateTest {

    @DisplayName("start, end 이외의 입력이 들어왔을 때 예외처리")
    @ParameterizedTest
    @NullSource
    void validateNotNull(String input) {
        assertThatThrownBy(() -> GameState.of(input))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("start, end 이외의 입력이 들어왔을 때 예외처리")
    @ParameterizedTest
    @EmptySource
    @CsvSource(value = {"no", "pause"})
    void otherInputTest(String input) {
        assertThatThrownBy(() -> GameState.of(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }
}
