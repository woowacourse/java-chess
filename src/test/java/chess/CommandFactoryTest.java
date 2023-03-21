package chess;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.command.CommandFactory;
import chess.command.EndCommand;
import chess.command.MoveCommand;
import chess.command.StartCommand;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {
    
    @Test
    @DisplayName("명령 정상 생성 테스트")
    void create_command_test() {
        Assertions.assertThat(CommandFactory.generateCommand(List.of("start"))).isInstanceOf(StartCommand.class);
        Assertions.assertThat(CommandFactory.generateCommand(List.of("end"))).isInstanceOf(EndCommand.class);
        Assertions.assertThat(CommandFactory.generateCommand(List.of("move", "a1", "b2")))
                .isInstanceOf(MoveCommand.class);
    }
    
    @Test
    @DisplayName("명령 생성 에러 테스트")
    void create_error_command() {
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("start", "a1")));
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("end", "a1")));
        assertThrows(IllegalArgumentException.class, () -> CommandFactory.generateCommand(List.of("move")));
        assertThrows(IllegalArgumentException.class,
                () -> CommandFactory.generateCommand(List.of("move", "a1", "b2", "c3")));
    }
}