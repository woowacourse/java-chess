package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END"}, delimiter = ':')
    void findCommand(String commandLine, GameCommand command) {
        assertThat(GameCommand.findCommand(commandLine)).isEqualTo(command);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sTaRT", "End", "hello"})
    void throwInvalidCommand(String commandLine) {
        assertThatThrownBy(() -> GameCommand.findCommand(commandLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("잘못된 게임 시작 커맨드입니다. %s", commandLine));
    }
}
