package chess.view.matcher;

import chess.domain.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandMatcherTest {

    @DisplayName("CommandMatcher는 모든 Command에 대한 매칭 정보를 포함한다")
    @Test
    void matchCommands() {
        for (Command value : Command.values()) {
            assertThat(CommandMatcher.isPresentCommand(value)).isTrue();
        }
    }
}
