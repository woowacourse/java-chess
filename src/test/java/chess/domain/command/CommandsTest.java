package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

class CommandsTest {

    @DisplayName("올바른 명령어 이름을 확인한다.")
    @ParameterizedTest
    @CsvSource({"move a1a2", "move a1 a23", "move a123 a2", "move a1 a1 a2"})
    void getIfPresent(String input) {
        Commands commands = new Commands(Arrays.asList(
                new EndCommand(null),
                new MoveCommand(null),
                new StartCommand(null),
                new StatusCommand(null)
        ));


        assertThatCode(() ->commands.getIfPresent(input))
                .doesNotThrowAnyException();
    }

}