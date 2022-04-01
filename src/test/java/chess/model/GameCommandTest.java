package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.ChessCommand;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END"}, delimiter = ':')
    void findCommand(String commandLine, ChessCommand command) {
        List<String> commandInput = List.of(commandLine);

        assertThat(ChessCommand.findCommand(commandInput)).isEqualTo(command);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sTaRT", "End", "hello"})
    void throwInvalidCommand(String commandLine) {
        List<String> command = List.of(commandLine);

        assertThatThrownBy(() -> ChessCommand.findCommand(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(String.format("잘못된 커맨드입니다. %s", command));
    }
}
