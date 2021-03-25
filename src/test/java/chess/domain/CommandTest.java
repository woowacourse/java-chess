package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {
    @DisplayName("커맨드 목록에 있는 커맨드 생성하기")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move", "status"})
    void of1(String command) {
        assertThatCode(() -> Command.of(command)).doesNotThrowAnyException();
    }

    @DisplayName("커맨드 목록에 없는 커맨드는 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"delete", "bye", "run"})
    void of2(String command) {
        assertThatThrownBy(() -> Command.of(command)).isInstanceOf(IllegalArgumentException.class);
    }
}