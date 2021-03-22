package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("Command 반환 : START")
    @Test
    void of_returnStart_success() {
        String value = "start";
        assertThat(Command.of(value)).isEqualTo(Command.START);
    }

    @DisplayName("Command 반환 : END")
    @Test
    void of_returnEnd_success() {
        String value = "end";
        assertThat(Command.of(value)).isEqualTo(Command.END);
    }

    @DisplayName("Command 반환 : MOVE")
    @Test
    void of_returnMove_success() {
        String value = "move";
        assertThat(Command.of(value)).isEqualTo(Command.MOVE);
    }

    @DisplayName("유저가 입력한 문자열이 start일 때만 true를 반환한다.")
    @Test
    void isStart() {
        String success_value = "start";
        String fail_value = "end";
        assertAll(
            () -> assertTrue(Command.isStart(success_value)),
            () -> assertFalse(Command.isStart(fail_value))
        );
    }

    @DisplayName("유저가 입력한 문자열이 end일 때만 true를 반환한다.")
    @Test
    void isEnd() {
        String success_value = "end";
        String fail_value = "start";
        assertAll(
            () -> assertTrue(Command.isEnd(success_value)),
            () -> assertFalse(Command.isEnd(fail_value))
        );
    }
}