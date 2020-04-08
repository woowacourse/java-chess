package chess.model.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChessGameStateAndMoveSquareTest {

    @DisplayName("생성자 Null 체크")
    @Test
    void validateNotNull() {
        assertThatThrownBy(() -> new GameStateAndMoveSquare(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @DisplayName("생성자 - 잘못된 인자")
    @ParameterizedTest
    @ValueSource(strings = {"start a1 a2", "move a1", "status move a2 a3", "en d"})
    void validateArgument(String input) {
        assertThatThrownBy(() -> new GameStateAndMoveSquare(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("State 비교")
    @ParameterizedTest
    @ValueSource(strings = {"start", "move a1 a2", "status", "end"})
    void isSameState(String input) {
        assertThat(new GameStateAndMoveSquare(input)
            .isSameState(GameState.of(input.split(" ")[0])))
            .isTrue();
    }
}