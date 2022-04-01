package chess.console.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.console.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CommandTest {

    @DisplayName("생성 확인")
    @ParameterizedTest
    @CsvSource(value = {"start:START", "Start:START", "START:START", "move:MOVE", "end:END"}, delimiter = ':')
    void create(String input, Command expect) {
        assertThat(Command.of(input)).isEqualTo(expect);
    }
}