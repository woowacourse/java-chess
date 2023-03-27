package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BootingCommandTest {
    @Test
    @DisplayName("주어진 커멘드에 알맞는 BootingCommand 객체를 가져온다.")
    void findByCommand() {
        assertAll(
                () -> assertThat(BootingCommand.findByCommand("start")).isEqualTo(BootingCommand.START),
                () -> assertThat(BootingCommand.findByCommand("end")).isEqualTo(BootingCommand.END)
        );
    }

    @Test
    @DisplayName("move 문법에 알맞는 명령어가 주어지면 알맞는 BootingCommand 객체를 가져온다.")
    void moveCommand() {
        assertThat(BootingCommand.findByCommand("move e3 e2"))
                .isEqualTo(BootingCommand.MOVE);
    }

    @Test
    @DisplayName("move 문법에 알맞지 않은 명령어가 주어지면 예외가 발생한다.")
    void invalidMoveCommand() {
        assertThatThrownBy(() -> BootingCommand.findByCommand("move e3e2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 커멘드에 알맞는 BootingCommand 객체가 없으면 예외를 반환한다.")
    void findByInvalidCommand() {
        assertThatThrownBy(() -> BootingCommand.findByCommand("invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}