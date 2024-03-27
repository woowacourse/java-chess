package view.command;

import command.Command;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("START 커멘드의 식별자와 일치하는지 확인한다.")
    @Test
    void isStartCommand() {
        Assertions.assertThat(Command.isStartCommand("start")).isTrue();
    }

    @DisplayName("MOVE 커멘드의 식별자와 일치하는 경우 True를 return 한다.")
    @Test
    void moveCommandIdentifierMove() {
        Assertions.assertThat(Command.isCommandMove("move")).isTrue();
    }

    @DisplayName("MOVE 커멘드의 식별자와 일치하지 않고 END인 경우 false를 return 한다.")
    @Test
    void moveCommandIdentifierEnd() {
        Assertions.assertThat(Command.isCommandMove("end")).isFalse();
    }
}
