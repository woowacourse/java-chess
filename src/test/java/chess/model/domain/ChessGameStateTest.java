package chess.model.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.state.GameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

public class ChessGameStateTest {

    @Test
    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> GameState.of(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @DisplayName("start, end 이외의 입력이 들어왔을 때 예외처리")
    @ParameterizedTest
    @EmptySource
    @CsvSource(value = {"no", "pause"})
    void otherInputTest(String input) {
        assertThatThrownBy(() -> GameState.of(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
