package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import chess.domain.command.GameCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameCommandTest {

    @ParameterizedTest
    @MethodSource("getGameCommand_TestCases")
    @DisplayName("커맨드를 받아서 게임 상태를 반환한다.")
    void getGameCommand(String command, GameCommand gameCommand) {
        // when, then
        assertThat(GameCommand.from(command)).isEqualTo(gameCommand);
    }

    static Stream<Arguments> getGameCommand_TestCases() {
        return Stream.of(
                Arguments.arguments("start", GameCommand.START),
                Arguments.arguments("move", GameCommand.MOVE),
                Arguments.arguments("end", GameCommand.END)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"star", "mo", "and"})
    @DisplayName("커맨드를 받아서 게임 상태를 반환한다.")
    void getGameCommand(String command) {
        // when, then
        assertThatThrownBy(() -> GameCommand.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 커맨드를 입력해주세요.");
    }
}
