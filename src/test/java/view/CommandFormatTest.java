package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.command.ChessCommand;
import service.command.EndCommand;
import service.command.MoveCommand;
import service.command.StatusCommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandFormatTest {

    @DisplayName("move b2 b3 형식은 MoveCommand를 만든다.")
    @Test
    void move() {
        // given
        final String input = "move b4 b5";

        // when
        final ChessCommand moveCommand = CommandFormat.createCommand(input);

        // then
        assertThat(moveCommand).isInstanceOf(MoveCommand.class);
    }


    @DisplayName("move b2 b3 형식에 맞지 않으면 안된다.")
    @Test
    void invalidMove() {
        // given
        final String input = "move bb cc";

        // when & then
        assertThatThrownBy(() -> CommandFormat.createCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 커맨드입니다.");

    }

    @DisplayName("end는 EndCommand를 만든다.")
    @Test
    void end() {
        // given
        final String input = "end";

        // when
        final ChessCommand endCommand = CommandFormat.createCommand(input);

        // then
        assertThat(endCommand).isInstanceOf(EndCommand.class);
    }

    @DisplayName("status는 StatusCommand를 만든다.")
    @Test
    void status() {
        // given
        final String input = "status";

        // when
        final ChessCommand statusCommand = CommandFormat.createCommand(input);

        // then
        assertThat(statusCommand).isInstanceOf(StatusCommand.class);
    }
}
