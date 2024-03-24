package view.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("START 커멘드의 식별자와 일치하는지 확인한다.")
    @Test
    void isStartCommand() {
        Assertions.assertThat(Command.isStartCommand("start")).isTrue();
    }

    @DisplayName("MOVE 커멘드의 식별자와 일치하는지 확인한다.")
    @Test
    void isMoveCommand() {
        Assertions.assertThat(Command.isMoveCommand("move")).isTrue();
    }

    @DisplayName("END 커멘드의 식별자와 일치하는지 확인한다.")
    @Test
    void isEndCommand() {
        Assertions.assertThat(Command.isEndCommand("end")).isTrue();
    }
}
