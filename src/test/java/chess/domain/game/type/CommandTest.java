package chess.domain.game.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.console.type.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {
    @DisplayName("문자열 명령어 입력으로 상수 가져오기")
    @ParameterizedTest
    @ValueSource(strings = {"start", "move", "status", "end"})
    void findCommandEnumByInput(String commandInput) {
        assertThat(Command.of(commandInput)).isSameAs(Command.valueOf(commandInput.toUpperCase()));
    }

    @DisplayName("유효하지 않은 문자열 명령어 입력으로 상수 가져올 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"startt", "m ove", "sstatus", "end "})
    void cannotFindCommandEnumByInvalidInput(String commandInput) {
        assertThatThrownBy(() -> Command.of(commandInput))
            .isInstanceOf(IllegalArgumentException.class);
    }
}