package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"START:start", "MOVE:move", "END:end", "STATUS:status"}, delimiter = ':')
    @DisplayName("command 확인")
    void checkCommand(Command command, String input) {
        assertThat(command.isValue(input)).isTrue();
    }

    @Test
    @DisplayName("source 위치 반환 확인")
    void moveSourceFormat() {
        assertThat(Command.toMoveSourceFormat("move a1 a2")).isEqualTo(new String[]{"a", "1"});
    }

    @Test
    @DisplayName("target 위치 반환 확인")
    void moveTargetFormat() {
        assertThat(Command.toMoveTargetFormat("move a1 a2")).isEqualTo(new String[]{"a", "2"});
    }
}
