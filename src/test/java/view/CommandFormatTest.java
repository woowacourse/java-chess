package view;

import domain.command.ChessCommand;
import domain.command.EndCommand;
import domain.command.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandFormatTest {

    @DisplayName("move b2 b3 형식은 MoveCommand를 만든다.")
    @Test
    void move() {
        // given
        final String input = "move b4 b5";

        // when
        final ChessCommand moveCommand = CommandFormat.createMoveCommand(input);

        // then
        assertThat(moveCommand).isInstanceOf(MoveCommand.class);
    }


    @DisplayName("move b2 b3 형식에 맞지 않으면 안된다.")
    @Test
    void invalidMove() {
        // given
        final String input = "move bb cc";

        // when & then
        assertThatThrownBy(() -> CommandFormat.createMoveCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 커맨드입니다.");

    }

    @DisplayName("end는 EndCommand를 만든다.")
    @Test
    void end() {
        // given
        final String input = "end";

        // when
        final ChessCommand moveCommand = CommandFormat.createMoveCommand(input);

        // then
        assertThat(moveCommand).isInstanceOf(EndCommand.class);
    }
}
