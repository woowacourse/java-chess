package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.command.CommandLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitializationTest {

    @DisplayName("start를 입력하면 게임이 시작된다.")
    @Test
    void gameStartWhenCommandIsStart() {
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = Initialization.gameSetting(startCommand);
        assertThat(gameStatus).isInstanceOf(Running.class);
    }

    @DisplayName("end를 입력하면 게임이 종료된다.")
    @Test
    void gameEndWhenCommandIsEnd() {
        final CommandLine endCommand = CommandLine.from(List.of("end"));
        final GameStatus gameStatus = Initialization.gameSetting(endCommand);
        assertThat(gameStatus).isInstanceOf(End.class);
    }

    @DisplayName("시작시 유효하지 않은 명령어가 오면 예외가 발생한다.")
    @Test
    void invalidCommand() {
        CommandLine moveCommand = CommandLine.from(List.of("move", "a2", "d3"));
        assertThatThrownBy(() -> Initialization.gameSetting(moveCommand))
                .isInstanceOf(InvalidStatusException.class);
    }
}
