package view.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.util.Command;

class CommandsTest {

    @DisplayName("받은 값을 Command 별로 나누어 저장한다.")
    @Test
    void getCommands() {
        Commands commands = new Commands(List.of("move", "a2", "a3"));

        Assertions.assertAll(
                () -> assertThat(commands.command()).isEqualTo(Command.MOVE),
                () -> assertThat(commands.startCoordinate()).isEqualTo(new CoordinateRequest(6, 0)),
                () -> assertThat(commands.destinationCoordinate()).isEqualTo(new CoordinateRequest(5, 0))
        );
    }
}
