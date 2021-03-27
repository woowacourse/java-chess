package chess.domain.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandsTest {
    private Commands commands;

    @BeforeEach
    void setUp() {
        this.commands = Commands.validCommands();
    }

    @DisplayName("command를 찾는다")
    @Test
    void command를_찾는다() {
        Command startCommand = commands.findCommandByText("start");

        assertThat(startCommand).isInstanceOf(StartOnCommand.class);
    }

    @DisplayName("command를 찾는다_명령어가 잘못되면 예외")
    @Test
    void command를_찾는다_예외() {
        assertThatThrownBy(() -> commands.findCommandByText("amazzi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("command를 찾는다")
    @Test
    void command를_찾는다1() {
        Command startCommand1 = commands.findCommandByText("end");
        Command startCommand2 = commands.findCommandByText("end");


        assertThat(startCommand1).isEqualTo(startCommand2);
    }
}
