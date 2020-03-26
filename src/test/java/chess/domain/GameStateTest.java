package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameStateTest {
    @ParameterizedTest
    @CsvSource(value = {"no", "pause"})
    @NullAndEmptySource
    @DisplayName("start, end 이외의 입력이 들어왔을 때 예외처리")
    void otherInputTest(String input) {
        assertThatThrownBy(() -> GameState.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
