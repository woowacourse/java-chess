package chess.domain.state.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.state.exception.CommandNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTypeTest {

    @DisplayName("존재하는 커맨드 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "status", "move a1 a2"})
    void create(String command) {
        final CommandType commandType = CommandType.from(command);
        assertThat(commandType).isEqualTo(CommandType.from(command));
    }

    @DisplayName("잘못된 커맨드 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"start two", "move", "end s", "status ab"})
    void create_invalidInput(String command) {
        assertThatThrownBy(() -> CommandType.from(command))
            .isInstanceOf(CommandNotFoundException.class);
    }
}
