package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"show", "pause"})
    @DisplayName("유효하지 않은 커맨드")
    void invalid(final String invalidCommand) {
        final var ChessGame = new ChessGame();

        assertThatThrownBy(() -> Command.execute(invalidCommand, ChessGame))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 명령어");
    }
}