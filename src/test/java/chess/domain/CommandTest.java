package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @ParameterizedTest
    @DisplayName("지원되는 명령어가 입력되면, true를 반환한다.")
    @ValueSource(strings = {"START", "END", "MOVE", "STATUS"})
    void validateCommandCheck(final String command) {
        assertThat(Command.validatesCommand(command)).isTrue();
    }

    @Test
    @DisplayName("지원하지 않는 명령어가 입력되면, false를 반환한다.")
    void wrongCommandCheck() {
        String wrongCommand = "pobi";
        assertThat(Command.validatesCommand(wrongCommand)).isFalse();
    }
}