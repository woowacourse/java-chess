package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.ChessCommand;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END"}, delimiter = ':')
    void findCommand(String commandLine, ChessCommand command) {
        assertThat(ChessCommand.findCommand(commandLine)).isEqualTo(command);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sTaRT", "End", "hello"})
    void throwInvalidCommand(String commandLine) {
        assertThatThrownBy(() -> ChessCommand.findCommand(commandLine))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(String.format("잘못된 커맨드입니다. %s", commandLine));
    }
}
