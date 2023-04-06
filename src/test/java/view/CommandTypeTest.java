package view;

import controller.command.CommandType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommandTypeTest {
    @Test
    @DisplayName("주어진 커멘드에 알맞는 BootingCommand 객체를 가져온다.")
    void findByCommand() {
        assertAll(
                () -> assertThat(CommandType.findByCommand("start")).isEqualTo(CommandType.START),
                () -> assertThat(CommandType.findByCommand("end")).isEqualTo(CommandType.END),
                () -> assertThat(CommandType.findByCommand("status")).isEqualTo(CommandType.STATUS)
        );
    }

    @Test
    @DisplayName("move 문법에 알맞는 명령어가 주어지면 알맞는 BootingCommand 객체를 가져온다.")
    void moveCommand() {
        assertThat(CommandType.findByCommand("move e3 e2"))
                .isEqualTo(CommandType.MOVE);
    }

    @Test
    @DisplayName("move 문법에 알맞지 않은 명령어가 주어지면 예외가 발생한다.")
    void invalidMoveCommand() {
        assertThatThrownBy(() -> CommandType.findByCommand("move e3e2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주어진 커멘드에 알맞는 BootingCommand 객체가 없으면 예외를 반환한다.")
    void findByInvalidCommand() {
        assertThatThrownBy(() -> CommandType.findByCommand("invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
