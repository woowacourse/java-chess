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
    @DisplayName("주어진 커멘드에 알맞는 BootingCommand 객체를 가져온다.")
    void findByInvalidCommand() {
        assertThatThrownBy(() -> BootingCommand.findByCommand("invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}