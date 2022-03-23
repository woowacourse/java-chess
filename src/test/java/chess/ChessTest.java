package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChessTest {

    @DisplayName("명령어가 예외일 경우")
    @Test
    void command_exception() {
        assertThatThrownBy(() ->
            Command.startEnd("not found command")
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }

    @DisplayName("명령어가 정상 입력")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end"})
    void command_normal(String input) {
        assertDoesNotThrow(() -> Command.startEnd(input));
    }
}
