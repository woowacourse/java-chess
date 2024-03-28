package chess.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @ParameterizedTest(name = "[{index}] {0}는 존재하는 명령어이다.")
    @ValueSource(strings = {"start", "move", "status", "end"})
    @DisplayName("전달받은 명령어가 존재하는지 확인한다.")
    void hasCommand(String command) {
        assertThat(Command.hasCommand(command)).isTrue();
    }

    @Test
    @DisplayName("전달받은 명령어가 존재하지 않으면 false를 반환한다.")
    void hasNotCommand() {
        assertThat(Command.hasCommand("exit")).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"START,start", "MOVE,move", "STATUS,status", "END,end"})
    @DisplayName("전달받은 명령어와 같은지 확인한다.")
    void sameWith(Command command, String input) {
        assertThat(command.sameWith(input)).isTrue();
    }

    @Test
    @DisplayName("전달받은 명령어로 시작하는지 확인한다.")
    void startsWith() {
        assertThat(Command.MOVE.startsWith("move a2 a4")).isTrue();
    }
}
